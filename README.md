# GolfMax-Web-API

### Current features
- Authentication - very basic and not secure yet, will improve later
- Updating the user's information
- Get user by id
- Get all users
- Delete user
- Get all scores
- Get scores by user id
- Add score
- Delete score
- MySQL database created with Hibernate

### Future features
- OpenID and OAuth 2.0 for a more secure authorization
- Password hashing
- Spring Security
- Deploying the API as a Docker container

### Routes
#### Scores
- GET /scores
- GET /scores/{id}
- POST /scores/add
- DELETE /scores/{id}

#### User
- POST /users/login
- POST /users/register
- GET /users
- GET /users/{id}
- PUT /users/{id}
- DELETE /users/{id}

#### Courses
- GET /courses
- GET /courses{id}
- POST /courses/new_course
- DELETE /courses/{id}
