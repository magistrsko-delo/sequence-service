package si.fri.mag.entities;

import javax.persistence.*;

@Entity
@Table(name = "sequence")

@NamedNativeQueries({
        @NamedNativeQuery(
                name = "getProjectSequences",
                query = ""
        ),
})
public class SequenceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sequence_id", nullable = false)
    private Integer sequenceId;

    @Column(name = "name", nullable = false)
    private String name;


    // getters

    public Integer getSequenceId() {
        return sequenceId;
    }

    public String getName() {
        return name;
    }


    // setters

    public void setSequenceId(Integer sequenceId) {
        this.sequenceId = sequenceId;
    }

    public void setName(String name) {
        this.name = name;
    }
}
