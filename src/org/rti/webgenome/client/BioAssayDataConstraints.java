package org.rti.webgenome.client;

import java.io.Serializable;

/**
 * @author Ram Bhattaru
 */

/**
 * This class can be used to specify Search Criteria for the BioAssay
 * data to be retrieved.
 */
public class BioAssayDataConstraints implements Serializable {
    private Long startPosition;
    private Long endPosition;
    private String chromosome;
    private String quantitationType;

    public Long getStartPosition() {
        return startPosition;
    }

    public Long getEndPosition() {
        return endPosition;
    }

    public String getChromosome() {
        return chromosome;
    }

    public String getQuantitationType() {
        return quantitationType;
    }

    public void setPositions(Long startPos, Long endPos) {
       this.startPosition = startPos;
       this.endPosition = endPos;
    }

    public void setChromosome(String chromsomeNumber) {
        this.chromosome = chromsomeNumber;
    }

    public void setQuantitationType(String quantitaionType) {
       this.quantitationType = quantitaionType;
    }
}
