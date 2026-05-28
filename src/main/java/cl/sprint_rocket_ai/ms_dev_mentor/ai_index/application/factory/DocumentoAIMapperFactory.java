package cl.sprint_rocket_ai.ms_dev_mentor.ai_index.application.factory;

import cl.sprint_rocket_ai.ms_dev_mentor.ai_index.application.strategy.AbstractDocumentoAIMapperStrategy;
import cl.sprint_rocket_ai.ms_dev_mentor.ai_index.application.strategy.DDLAIMapperStrategy;
import cl.sprint_rocket_ai.ms_dev_mentor.ai_index.application.strategy.LineamientoAIMapperStrategy;
import cl.sprint_rocket_ai.ms_dev_mentor.ai_index.application.strategy.NegocioAIMapperStrategy;
import cl.sprint_rocket_ai.ms_dev_mentor.ai_index.application.strategy.TecnicoAIMapperStrategy;
import cl.sprint_rocket_ai.ms_dev_mentor.commons.domain.enums.TipoDocumento;
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
    private final TecnicoAIMapperStrategy tecnicoStrategy;
    private final NegocioAIMapperStrategy negocioStrategy;
    private final DDLAIMapperStrategy ddlStrategy;

    public DocumentoAIMapperFactory(LineamientoAIMapperStrategy lineamientoStrategy,
                                    TecnicoAIMapperStrategy tecnicoStrategy,
                                    NegocioAIMapperStrategy negocioStrategy,
                                    DDLAIMapperStrategy ddlStrategy) {
        this.lineamientoStrategy = lineamientoStrategy;
        this.tecnicoStrategy = tecnicoStrategy;
        this.negocioStrategy = negocioStrategy;
        this.ddlStrategy = ddlStrategy;
    }

    public AbstractDocumentoAIMapperStrategy getMapperByDocumentType(TipoDocumento tipo) {
        return switch (tipo) {
            case LINEAMIENTO -> lineamientoStrategy;
            case TECNICO     -> tecnicoStrategy;
            case NEGOCIO     -> negocioStrategy;
            case DDL         -> ddlStrategy;
        };
    }
}
