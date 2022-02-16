cp ../target/cake-manager*.jar cakemanager.jar
docker build -t nikcross/cakemanager:latest .
docker kill cakemanager
docker rm cakemanager
docker run --name cakemanager -p 8080:8080 -d nikcross/cakemanager:latest
