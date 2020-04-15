package si.fri.mag.DTO.abstracts;


public abstract class SequenceAbstracts {
    private String name;
    private Integer projectId;
    private String thumbnail;

    public String getName() {
        return name;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public String getThumbnail() {
        return thumbnail;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
