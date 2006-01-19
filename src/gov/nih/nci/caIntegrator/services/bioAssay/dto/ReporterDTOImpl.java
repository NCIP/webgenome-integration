package gov.nih.nci.caIntegrator.services.bioAssay.dto;

import org.rti.webgenome.client.ReporterDTO;

/**
 * @author Ram Bhattaru
 */



public class ReporterDTOImpl implements ReporterDTO {
    private String name;
    private String chromosome;
    private Long chromosomeLocation;
    private String[] associatedGenes;
    private String[] annotations;
    private Boolean selected;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChromosome() {
        return chromosome;
    }

    public void setChromosome(String chromosome) {
        this.chromosome = chromosome;
    }

    public Long getChromosomeLocation() {
        return chromosomeLocation;
    }

    public void setChromosomeLocation(Long chromosomeLocation) {
        this.chromosomeLocation = chromosomeLocation;
    }

    public String[] getAssociatedGenes() {
        return associatedGenes;
    }

    public void setAssociatedGenes(String[] associatedGenes) {
        this.associatedGenes = associatedGenes;
    }

    public String[] getAnnotations() {
        return annotations;
    }

    public void setAnnotations(String[] annotations) {
        this.annotations = annotations;
    }

    public Boolean isSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }
}
