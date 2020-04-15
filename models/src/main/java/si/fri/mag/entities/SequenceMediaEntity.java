package si.fri.mag.entities;

import si.fri.mag.interfaces.MainEntity;

import javax.persistence.*;

@Entity
@Table(name = "sequence_media")

@NamedNativeQueries({
        @NamedNativeQuery(
                name = "getSequenceMedias",
                query = "select * from sequence_media where sequence_media.fk_sequence_id = ?1",
                resultClass = SequenceMediaEntity.class
        ),
        @NamedNativeQuery(
                name = "deleteSequenceMedia",
                query = "DELETE FROM sequence_media WHERE sequence_media.fk_sequence_id = ?1"
        ),
        @NamedNativeQuery(
                name = "deleteOneMediaInSequence",
                query = "DELETE FROM sequence_media WHERE sequence_media.fk_sequence_id = ?1 and sequence_media.fk_media_id = ?2"
        ),
})
public class SequenceMediaEntity implements MainEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "fk_sequence_id", nullable = false)
    private SequenceEntity sequenceEntity;

    @Column(name = "fk_media_id", nullable = false)
    private Integer mediaId;

    //getters


    public Integer getId() {
        return id;
    }

    public SequenceEntity getSequenceEntity() {
        return sequenceEntity;
    }

    public Integer getMediaId() {
        return mediaId;
    }

    // setters


    public void setId(Integer id) {
        this.id = id;
    }

    public void setMediaId(Integer mediaId) {
        this.mediaId = mediaId;
    }

    public void setSequenceEntity(SequenceEntity sequenceEntity) {
        this.sequenceEntity = sequenceEntity;
    }

}
