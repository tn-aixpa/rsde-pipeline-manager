package it.smartcommunitylabdhlab.rsde.common.bean;

/**
 * 
 * @author engg_nawaz { "project":"parcheggi-scheduler-2025", "kind":"hera+run",
 *         "spec":{
 *         "task":"hera+pipeline://parcheggi-scheduler-2025/b874787923cb4ff98aa9258e1a9b31bd",
 *         "local_execution":false,
 *         "workflow":"hera://parcheggi-scheduler-2025/pipeline_latest_parkings:118af59e538947c1b3b98f27d9f95796"}
 *         }
 */
public class DHRun {
    private String id;
    private static final String kind = "hera+run";
    private String name;
    private String project;
    private Spec spec;
    private Status status;

    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
    }

    public String getKind() {
	return kind;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getProject() {
	return project;
    }

    public void setProject(String project) {
	this.project = project;
    }

    public Spec getSpec() {
	return spec;
    }

    public void setSpec(Spec spec) {
	this.spec = spec;
    }

    public Status getStatus() {
	return status;
    }

    public void setStatus(Status status) {
	this.status = status;
    }

    public DHRun() {
    }

}
