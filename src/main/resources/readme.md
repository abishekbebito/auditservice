*list all tasklist
*filter the list based on the assignee...
STEP:1::this is to access tasklist api
mainly for listing the tasklist

1st get the bearer token


curl --request POST "https://login.cloud.camunda.io/oauth/token" \
--header "Content-Type: application/x-www-form-urlencoded" \
--data-urlencode "grant_type=client_credentials" \
--data-urlencode "audience=tasklist.camunda.io" \
--data-urlencode "client_id=XeJBLWXapAPTYJ6XNI5rg1~buxk0QDam" \
--data-urlencode "client_secret=JikyU_27lZewKDPjex2ZBqgLSUUBWGEzc-QtS5zIwwFI1qBE9a7dbXUyj93yPzI7"


after getting access token
2.call a api for example serach tasklist

CAMUNDA_TASKLIST_BASE_URL=https://${REGION}.tasklist.camunda.io/${CLUSTER_ID}.=https://sin-1.tasklist.camunda.io/eb946ae8-20e2-4cee-bf2f-218afc9621da

curl --request POST ${CAMUNDA_TASKLIST_BASE_URL}/v1/tasks/search \
--header "Authorization: Bearer ${TOKEN}" \
--header 'Content-Type: application/json' \
--data-raw '{}'


from java side using restclient
for frontend using ajax resaon-->after the page loads clicking search button tasklist alone filter itself...which is dynamic

1st-->send a new request..
