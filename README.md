# GolfMax-Web-API

### Current features
- Authentication
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
- Deploying API onto the cloud

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

#### Player Statistics
- GET /stats/user/{id}
- PUT /stats/{id}

#### Courses
- GET /courses
- GET /courses{id}
- POST /courses/new_course
- DELETE /courses/{id}
