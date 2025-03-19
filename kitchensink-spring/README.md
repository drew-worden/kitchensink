# Kitchen Sink Quickstart Application

## Prerequisites
- Java 21
- Maven 3.6.3
- MongoDB (either a local instance or a cloud-hosted instance)

## Running Locally
1. Clone the repository
2. Provide the necessary environment variables in a `.env` file in the root of the project. The following environment variables are required:
    - `DB_URI`: The URI of the MongoDB database to connect to.
3. Run using Maven and Spring Boot: `mvn spring-boot:run`

## Deploying to a Container Environment
1. Build the application using Maven: `mvn clean package`
2. Build the Docker image: `docker build -t kitchensink-spring .`
3. Run the Docker container: `docker run
4. Provide the necessary environment variables to the container using the `-e` flag. The following environment variables are required:
    - `DB_URI`: The URI of the MongoDB database to connect to.
    - `PORT`: The port to run the application on. Defaults to 8080.
5. Expose the port to the host machine using the `-p` flag: `docker run -e DB_URI=<DB_URI> -e PORT=<PORT> -p <HOST_PORT>:<CONTAINER_PORT> kitchensink-spring`
6. Access the application at `http://localhost:<HOST_PORT>`
7. To stop the container, use `docker stop <CONTAINER_ID>`

## License
The application is made available under the MIT license. See the LICENSE file at the root of the repository for more details.
