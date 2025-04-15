# Tune-Tracker-API 🎧
A RESTful API for managing music artists, albums, and songs. Built with Spring Boot, this API provides a complete solution for music catalog management.
## Features
- Artist, Album, and Song management with full CRUD operations
- MySQL database integration
- Docker containerization for easy deployment
- Comprehensive test coverage
- RESTful API design
- Postman collection for easy API testing
## Table of Contents
1. [Setup Instructions](#setup-instructions)
2. [Database Configuration](#database-configuration)
3. [Usage Instructions](#usage-instructions)
4. [API Documentation](#api-documentation)
5. [Testing with Postman](#testing-with-postman)
6. [License](#license)
7. [Author](#author)
## Setup Instructions
### Using Docker (Recommended)
```bash
docker-compose up --build
```
This will start both the MySQL database and the Spring Boot application.
### Manual Setup
1. Ensure you have Java 23 and Maven installed
2. Clone the repository
3. Run:
```bash
mvn clean install
mvn run
```
## Database Configuration

The application uses MySQL as its database. You can configure the database connection in the `application.properties` file:

```properties
# Database Configuration
spring.datasource.url=jdbc:mysql://[DATABASE_HOST]:[PORT]/[DATABASE_NAME]
spring.datasource.username=[USERNAME]
spring.datasource.password=[PASSWORD]
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```

### Configuration Options:

1. **Local Development**:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/tune-tracker-db
   spring.datasource.username=root
   spring.datasource.password=yourpassword
   ```

2. **Cloud Deployment (AWS RDS example)**:
   ```properties
   spring.datasource.url=jdbc:mysql://your-instance.region.rds.amazonaws.com:3306/tune-tracker-db
   spring.datasource.username=dbuser
   spring.datasource.password=dbpassword
   ```

### Security Best Practices:

- Never commit your actual database credentials to version control
- Use environment variables or a secure vault for production credentials
- For local development, you can use `application-dev.properties` which can be excluded from git

### Using Environment Variables:

```properties
spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
```

Then set these environment variables in your deployment environment or IDE configuration.

## Usage Instructions
The API can be accessed through multiple endpoints:

1. Local development: `http://localhost:8080`
2. EC2 example deployment: `http://[EC2-PUBLIC-IP]:80` - Replace `[EC2-PUBLIC-IP]` with your instance's public IPv4 address (found in your EC2 console)
3. Custom deployment: Configure your own endpoint as needed

### Database Access
- MySQL runs on port 3306 locally
- Default credentials are configured in docker-compose.yml
- For AWS deployments, use the RDS endpoint provided in your AWS console

## API Documentation
### Artists
- `GET /artists` - Retrieve all artists
- `GET /artist/{id}` - Get specific artist details
- `GET /artist_search` - Search artists by criteria
- `POST /artist` - Create a new artist
- `PUT /artist/{id}` - Update existing artist
- `DELETE /artist/{id}` - Remove an artist
### Albums
- `GET /albums` - Retrieve all albums
- `GET /album/{id}` - Get specific album details
- `GET /album_search` - Search albums by criteria
- `POST /album` - Create a new album
- `PUT /album/{id}` - Update existing album
- `DELETE /album/{id}` - Remove an album
### Songs
- `GET /songs` - Retrieve all songs
- `GET /song/{id}` - Get specific song details
- `GET /song_search` - Search songs by criteria
- `POST /song` - Create a new song
- `PUT /song/{id}` - Update existing song
- `DELETE /song/{id}` - Remove a song
## Testing with Postman
The API can be easily tested using Postman. A Postman collection file (`Tune-Tracker-API.postman_collection.json`) is provided in the project root, which includes:
- Pre-configured requests for all API endpoints
- Sample request bodies for POST and PUT operations
- Example test data for artists, albums, and songs
- Environment variables for easy configuration

To use the Postman collection:
1. Import the collection file into Postman
2. The collection includes a variable `baseUrl` with these options:
   - Local testing: Set to `http://localhost:8080`
   - EC2 deployment: Set to `http://[EC2-PUBLIC-IP]:80` (default in the provided collection)
   - Custom deployment: Update to your own URL as needed
3. Start testing the API endpoints with the provided examples

To change the base URL:
1. In Postman, go to the collection's variables
2. Update the `baseUrl` variable value
3. Save your changes
4. All requests will now use the new base URL

## License
This project is provided for personal use only. Redistribution, modification, or commercial use in any form is strictly prohibited without prior written permission from the author.
For detailed license terms, refer to the [LICENSE](./LICENSE.md) file.
## Author 
- **[Adam-S988](https://github.com/Adam-S988)** 
- **[BJamesShea](https://github.com/BJamesShea)** 
- **[Nasser-A-Ali](https://github.com/Nasser-A-Ali)**
- **[sarwoodford](https://github.com/sarwoodford)** 
- **[SearchingSteve](https://github.com/SearchingSteve)**
