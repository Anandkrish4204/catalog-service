#Build
custom_build(
    ref = 'ghcr.io/anandkrish4204/catalog-service:latest',
    command = './mvnw clean package docker:build',
    deps = ['pom.xml','src']
)



#Deploy

k8s_yaml(['k8s/deployment.yml','k8s/service.yml'])


#Manage

k8s_resource('catalog-service', port_forwards=['9001'])