package cl.sprint_rocket_ai.ms_context_builder.ai_index.application.factory;

import cl.sprint_rocket_ai.ms_context_builder.ai_index.application.strategy.*;
import cl.sprint_rocket_ai.ms_context_builder.commons.domain.enums.TipoDocumento;
import org.springframework.stereotype.Component;

/**
 * Factory que recibe todas las clases hijas de {@link AbstractDocumentoAIMapperStrategy}
 * inyectadas por Spring y decide cuál usar según el {@link TipoDocumento}.
 *
 * <p>La elección se hace con un {@code switch} sobre el enum: así, al añadir
 * un nuevo valor en {@link TipoDocumento}, el compilador obliga a registrar
 * la estrategia correspondiente (switch exhaustivo).</p>
 */
@Component
public class DocumentoAIMapperFactory {

    private final LineamientoAIMapperStrategy lineamientoStrategy;
    private final SistemaAIMapperStrategy sistemaStrategy;
    private final NegocioAIMapperStrategy negocioStrategy;
    private final DDLAIMapperStrategy ddlStrategy;

    public DocumentoAIMapperFactory(LineamientoAIMapperStrategy lineamientoStrategy,
                                    SistemaAIMapperStrategy sistemaStrategy,
                                    NegocioAIMapperStrategy negocioStrategy,
                                    DDLAIMapperStrategy ddlStrategy) {
        this.lineamientoStrategy = lineamientoStrategy;
        this.sistemaStrategy = sistemaStrategy;
        this.negocioStrategy = negocioStrategy;
        this.ddlStrategy = ddlStrategy;
    }

    public AbstractDocumentoAIMapperStrategy getMapperByDocumentType(TipoDocumento tipo) {
        return switch (tipo) {
            case LINEAMIENTO -> lineamientoStrategy;
            case SISTEMA     -> sistemaStrategy;
            case NEGOCIO     -> negocioStrategy;
            case DDL         -> ddlStrategy;
        };
    }
}
