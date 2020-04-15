package si.fri.mag.entities;

import si.fri.mag.interfaces.MainEntity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "sequence")

@NamedNativeQueries({
        @NamedNativeQuery(
                name = "getProjectSequences",
                query = "select * from sequence where sequence.fk_project_id = ?1",
                resultClass = SequenceEntity.class
        ),
        @NamedNativeQuery(
                name = "deleteSequence",
                query = "DELETE FROM sequence WHERE sequence.sequence_id = ?1"
        ),
})
public class SequenceEntity implements MainEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sequence_id", nullable = false)
    private Integer sequenceId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "status", nullable = false)
    private Integer status;

    @Column(name = "fk_project_id", nullable = false)
    private Integer fkProjectId;

    @Column(name = "thumbnail", nullable = false)
    private String thumbnail;

    @Column(name = "created_at", nullable = false, updatable = false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date createdAt;

    @Column(name = "updated_at", nullable = false, updatable = false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date updatedAt;

    // getters

    public Integer getSequenceId() {
        return sequenceId;
    }

    public String getName() {
        return name;
    }

    public Integer getStatus() {
        return status;
    }

    public Integer getFkProjectId() {
        return fkProjectId;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    // setters

    public void setSequenceId(Integer sequenceId) {
        this.sequenceId = sequenceId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setFkProjectId(Integer fkProjectId) {
        this.fkProjectId = fkProjectId;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
