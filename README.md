# RS Pipeline Manager
![GitHub Actions Workflow Status](https://img.shields.io/github/actions/workflow/status/tn-aixpa/rsde-pipeline-manager/maven.yaml?event=release) [![license](https://img.shields.io/badge/license-Apache%202.0-blue)](https://github.com/tn-aixpa/rsde-pipeline-manager/LICENSE) ![GitHub Release](https://img.shields.io/github/v/release/tn-aixpa/rsde-pipeline-manager)
![Status](https://img.shields.io/badge/status-beta-silver)

Remote Sensing Pipeline manager provides a GUI and an API for managing the remote sensing data pipelines. It is possible to start and monitor the pipeline execution, download the resulting products.

Each pipeline execution, or **elaboration**, represents an instance of a pipeline and is defined with

- reference to the pipeline instance in the platform
- name of the elaboration
- list of parameters (e.g., defined using Web UI)
- status (one of ``RUNNING``, ``COMPLETE``, ``ERROR``)

## Quick Start

To run locally the application in development mode, Java 17+, Apache Maven, and Node.JS are required.

### Back-end
Build the Spring Boot application:

```bash
mvn clean package
```

Run the application
```bash
java -jar target/rsde-pipeline-manager-0.0.1-SNAPSHOT.jar
```

The application API is available at ``http://localhost:8080/swagger-ui/index.html``. 

### Front-end

Go to the ``frontend`` directory and install the necessary dependencies
```
npm install
```

Then, run the front-end (make sure to `cd` to `frontend` first):
```
npm start
```
The front-end is available at ``http://localhost:4200/``. 

## Configuration

### Back-end

Back-end configuration properties are defined in the ``src/main/resources/application.yml`` and may also be passed as the corresponding environment variables.

Specifically, it is possible to define the DB configuration using standard JDBC properties

```yaml
jdbc:
  dialect: ${JDBC_DIALECT:org.hibernate.dialect.H2Dialect}
  driver: ${JDBC_DRIVER:org.h2.Driver}
  url: ${JDBC_URL:jdbc:h2:file:./data/db}
  user: ${JDBC_USER:rsde}
  password: ${JDBC_PASS:rsde}
  show-sql: false
  max-pool-size: ${JDBC_MAX_POOL_SIZE:10}
  min-pool-size: ${JDBC_MIN_POOL_SIZE:10}
  idle-timeout: ${JDBC_IDLE_TIMEOUT:600000}
  keep-alive-timeout: ${JDBC_KEEP_ALIVE_TIMEOUT:0}
  connection-timeout: ${JDBC_CONNECTION_TIMEOUT:30000}
  data-source-properties: {}
``` 
To connect the execution to the digitalhub platform, the following properties should be provided:

- ``core.token (ACCESS_TOKEN)``: Core access token (e.g., Personal Access Token) of long duration. 
- ``core.apiEndpoint (DHCORE_ENDPOINT)``: Core endpoint address.
- ``core.configUrl (CONFIG_URL)``: URL of the configuration file (e.g. , http://, file://, or classpath: schemas).

### Pipeline configuration
The pipeline configurations should be provided as a YAML file in the following manner
```yaml
elaborations:
  - name: flood
    projectId: protezione-civile
    taskId: kfp+pipeline://protezione-civile/d1a561c8929e4a0ba851c937d80c86ed
    workflowId: kfp://protezione-civile/protezione_civile:9d571837bfab4797ac5c6d2bc5e5d411
    tag: water
    fixedParameters:
      shapeArtifactName: bosco
      dataArtifactName: data_s2_deforestation
```
Here

- ``name`` is the unique name for the pipeline type. To each type a corresponding UI should be provided.
- ``projectId`` defines the platform project where the elaboration is performed
- `taskId`` and ``workflowId`` identify the instances of the workflow and the corresponding pipeline task to execute.
- ``tag`` used to group the workflows for security groups
- ``fixedParameters`` define the values of the parameters that are passed statically as the pipeline parameters.

An execution of the pipeline is configured with a set of specific parameters with the corresponding form. See the implementation of ``flood`` and ``forest`` UIs for reference.

### Front-end
The configuration of the front-end environments is made through the standard Angular environments. Only ``SERVER_URL`` defining the reference to the backend API should be configured.

## Development

The repo contains both the back-end code and the front-end.

The backend-application is implemented as a Java Spring Boot application (see ``src`` folder for the code). Dependencies are defined in ``pom.xml``.

The front-end application (``frontend`` folder) is implemented using Angular framework. Dependencies are defined in ``frontend/package.json``.

### Build container images

To build a docker image 

```bash
docker build -t rsde-pipeline-manager:VERSION .
```

To run a docker container

```bash
docker run -p 8080:8080 -t rsde-pipeline-manager:VERSION
```

Pass the configuration properties as environment variables.

### Run within the platform

Define the container function
```python
import digitalhub as dh

proj = dh.get_or_create_project("protezione-civile")

function_rs = proj.new_function(
    "rsde-pipeline-manager",
    kind="container",
    image="rsde-pipeline-manager:VERSION")
```

Create project secret with the long token (e.g., personal access token) named ``DHCORE_ACCESS_TOKEN``.

Run the function as the ``serve`` action
```python
function_rs.run(
	action="serve",
	secrets=["DHCORE_ACCESS_TOKEN"],
        envs=[{"name": "DHCORE_ENDPOINT", "value": "https://core.example.com"}, {"name": "CONFIG_URL", "value": "[https://core.example.com](https://raw.githubusercontent.com/tn-aixpa/rsde-pipeline-manager/refs/heads/main/config.yml)"}],
	service_type="ClusterIP",
	service_ports=[{"port": 8080, "target_port": 8080}]
)
```

## Security Policy

The current release is the supported version. Security fixes are released together with all other fixes in each new release.

If you discover a security vulnerability in this project, please do not open a public issue.

Instead, report it privately by emailing us at [digitalhub@fbk.eu](mailto:digitalhub@fbk.eu). Include as much detail as possible to help us understand and address the issue quickly and responsibly.

## Contributing

To report a bug or request a feature, please first check the existing issues to avoid duplicates. If none exist, open a new issue with a clear title and a detailed description, including any steps to reproduce if it's a bug.

To contribute code, start by forking the repository. Clone your fork locally and create a new branch for your changes. Make sure your commits follow the [Conventional Commits v1.0](https://www.conventionalcommits.org/en/v1.0.0/) specification to keep history readable and consistent.

Once your changes are ready, push your branch to your fork and open a pull request against the main branch. Be sure to include a summary of what you changed and why. If your pull request addresses an issue, mention it in the description (e.g., “Closes #123”).

Please note that new contributors may be asked to sign a Contributor License Agreement (CLA) before their pull requests can be merged. This helps us ensure compliance with open source licensing standards.

We appreciate contributions and help in improving the project!

## Authors

This project is developed and maintained by **DSLab – Fondazione Bruno Kessler**, with contributions from the open source community. A complete list of contributors is available in the project’s commit history and pull requests.

For questions or inquiries, please contact: [digitalhub@fbk.eu](mailto:digitalhub@fbk.eu)

## Copyright and license

Copyright © 2025 DSLab – Fondazione Bruno Kessler and individual contributors.

This project is licensed under the Apache License, Version 2.0.
You may not use this file except in compliance with the License. Ownership of contributions remains with the original authors and is governed by the terms of the Apache 2.0 License, including the requirement to grant a license to the project.
