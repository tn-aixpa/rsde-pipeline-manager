# rsde-pipeline-manager


##  Create Elaboration

The first time elaboration is created, the pipeline is invoked

```
    http://localhost:8020/elaboration/
```
    
POST Body

```
{
	"geomtry": "POLYGON((10.98014831542969 45.455314263477874,11.030273437500002 	45.44808893044964,10.99937438964844 	45.42014226680115,10.95302581787109645.435803739956725,10.98014831542969 	45.455314263477874))",
	"projectId": "parcheggi-scheduler-2025",
	"taskId": "kfp+pipeline://parcheggi-scheduler-2025/e933172794e446298fc3e16a5fd25a7d",
	"workflowId":"kfp://parcheggi-scheduler-2025/pipeline_latest_parkings:e983d1792ffb484f9f44a2eb06d54826"	,
	"name": "pipeline_lastest_parkings",
	"artifactName": "pipeline-data",
	"eventDate": "2024-10-20",
	"type": "land"
}
```


## List Elaboration
Get list of saved elaboration

```
	http://localhost:8020/elaboration
```

## Run Elaboration
Run elaboration on platform

POST 

```
	http://localhost:8020/elaboration/start/{elaboration Id}
```

The elaboration are saved with id prefix 'elb-' for e.g. elb-6f6e3f71-ffd2-4c94-9daa-676e9db04635

## List Templates
List elaboration templates for creation

```
	http://localhost:8020/templates/
```

