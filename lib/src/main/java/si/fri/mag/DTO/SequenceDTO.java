package si.fri.mag.DTO;

import si.fri.mag.DTO.abstracts.SequenceAbstracts;
import si.fri.mag.DTO.interfaces.SequenceInterface;

import java.sql.Date;

public class SequenceDTO extends SequenceAbstracts implements SequenceInterface {
    private Integer sequenceId;
    private Integer status;
    private Date createdAt;
    private Date updatedAt;

    // getters


    public Integer getSequenceId() {
        return sequenceId;
    }

    public Integer getStatus() {
        return status;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    // setters

    public void setSequenceId(Integer sequenceId) {
        this.sequenceId = sequenceId;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
