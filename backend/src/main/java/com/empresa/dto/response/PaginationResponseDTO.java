package com.empresa.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * DTO para respuestas paginadas
 * @param <T> Tipo de los elementos en el contenido
 */
@Getter
@ToString
@Builder(builderMethodName = "hiddenBuilder", builderClassName = "PaginationResponseDTOBuilder")
public final class PaginationResponseDTO<T> {
    private final List<T> content;
    private final int currentPage;
    private final long totalItems;
    private final int totalPages;

    /**
     * Método builder público con validaciones
     */
    public static <T> PaginationResponseDTOBuilder<T> builder() {
        return new PaginationResponseDTOBuilder<T>() {
            @Override
            public PaginationResponseDTO<T> build() {
                // Validaciones
                Objects.requireNonNull(super.content, "El contenido no puede ser nulo");
                
                if (super.currentPage < 0) {
                    throw new IllegalArgumentException("La página actual no puede ser negativa");
                }
                
                if (super.totalItems < 0) {
                    throw new IllegalArgumentException("El total de items no puede ser negativo");
                }
                
                if (super.totalPages < 0) {
                    throw new IllegalArgumentException("El total de páginas no puede ser negativo");
                }
                
                if (super.currentPage >= super.totalPages && super.totalPages > 0) {
                    throw new IllegalArgumentException("La página actual no puede ser mayor o igual al total de páginas");
                }
                
                if (super.content.size() > super.totalItems) {
                    throw new IllegalArgumentException("El tamaño del contenido no puede ser mayor que el total de items");
                }

                return new PaginationResponseDTO<>(
                    super.content,
                    super.currentPage,
                    super.totalItems,
                    super.totalPages
                );
            }
        };
    }

    /**
     * Método factory para creación conveniente
     */
    public static <T> PaginationResponseDTO<T> of(
        List<T> content,
        int currentPage,
        long totalItems,
        int totalPages
    ) {
        return PaginationResponseDTO.<T>builder()
            .content(content)
            .currentPage(currentPage)
            .totalItems(totalItems)
            .totalPages(totalPages)
            .build();
    }

    /**
     * Constructor privado para forzar uso del builder
     */
    private PaginationResponseDTO(
        List<T> content,
        int currentPage,
        long totalItems,
        int totalPages
    ) {
        this.content = Collections.unmodifiableList(content);
        this.currentPage = currentPage;
        this.totalItems = totalItems;
        this.totalPages = totalPages;
    }

    // Métodos adicionales útiles

    public boolean hasNext() {
        return currentPage < totalPages - 1;
    }

    public boolean hasPrevious() {
        return currentPage > 0;
    }

    public boolean isEmpty() {
        return content.isEmpty();
    }
}