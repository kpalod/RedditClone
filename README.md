# ShareSpace
ShareSpace is a community-driven content sharing platform built with Spring Boot in Java, offering a familiar and engaging experience for users to discover and discuss topics of interest. :speech_balloon: :earth_americas:


## Features
🌐 Communities: Create, join, and explore various communities centered around specific topics.

📝 Posts: Submit and browse posts within communities, sharing valuable content and ideas.

💬 Comments: Engage in discussions by commenting on posts and replying to other users' comments.

👍👎 Upvotes/Downvotes: Express your opinion on posts and comments by upvoting or downvoting them.

🔒 Secure Login: Utilize Spring Security 2.0 to ensure safe and protected user authentication.

💾 Database Storage: Leverage AWS RDS to store and manage user data, posts, and community information.

🚀 Deployment with AWS EC2: Application packaged as a JAR file and deployed on AWS EC2.


## Technolgies used  :computer:
- Java
- Spring Boot
- Spring Security
- AWS RDS (with mysql)
- AWS EC2 (linux instance)

## API Endpoints :electric_plug:

<details>
<summary>Vote Controller</summary>

<details>
<summary>POST /api/votes/ </summary>

**Description:** Create a new vote.

**Request Body:**

```json
{
  "voteType": "UPVOTE",
  "postID": 12345
}
```
**Response**:
```json
{
  "message": "Vote created successfully."
}
```


</details>
</details>
<details>
<summary>Subreddit Controller</summary>
<details>
<summary>GET `/api/subreddit`</summary>
Description: Retrieve all subreddits.

**Response**:
```json
[
  {
    "id": 1,
    "name": "example_subreddit",
    "description": "Example Subreddit",
    "numberOfPosts": 10
  },
  {
    "id": 2,
    "name": "another_subreddit",
    "description": "Another Subreddit",
    "numberOfPosts": 5
  }
]
```
</details>
<details>
<summary>POST `/api/subreddit`</summary>
Description: Create a new subreddit.
  
**Request Body**:
  
```json
{
  "name": "new_subreddit",
  "description": "New Subreddit"
}
```
**Response**:
```json
{
  "id": 3,
  "name": "new_subreddit",
  "description": "New Subreddit",
  "numberOfPosts": 0
}
```
</details>
<details>
<summary>GET `/api/subreddit/{id}`</summary>
  
**Description**: Get details of a specific subreddit.

Parameters:
id (path parameter) - The ID of the subreddit.
**Response**:
```json
{
  "id": 1,
  "name": "example_subreddit",
  "description": "Example Subreddit",
  "numberOfPosts": 10
}
```
</details>
</details>
<details>
  
<summary>Post Controller</summary>
<details>
  
<summary>POST `/api/posts`</summary>

**Description**: Create a new post.

**Request Body**:

```json
{
  "postID": 12345,
  "subredditName": "example_subreddit",
  "postName": "Example Post",
  "url": "https://example.com",
  "description": "Example description"
}
```
**Response**:

```json
{
  "message": "Post created successfully."
}
```
</details>
<details>
  
<summary>GET `/api/posts/{id}`</summary>

**Description**: Get details of a specific post.

Parameters:

- id (path parameter) - The ID of the post.

**Response**:

```json
{
  "id": 12345,
  "postName": "Example Post",
  "url": "https://example.com",
  "description": "Example description",
  "userName": "example_user",
  "subredditName": "example_subreddit",
  "voteCount": 10,
  "commentCount": 5,
  "duration": "1 day ago",
  "upVote": true,
  "downVote": false
}
```
</details>
<details>
<summary>GET `/api/posts/by-user/{username}`</summary>

**Description:** Get posts by username.

Parameters:

- username (path parameter) - The username of the user.

**Response**:
```json
[
  {
    "id": 12345,
    "postName": "Example Post",
    "url": "https://example.com",
    "description": "Example description",
    "userName": "example_user",
    "subredditName": "example_subreddit",
    "voteCount": 10,
    "commentCount": 5,
    "duration": "1 day ago",
    "upVote": true,
    "downVote": false
  },
  {
    "id": 54321,
    "postName": "Another Post",
    "url": "https://another.com",
    "description": "Another description",
    "userName": "example_user",
    "subredditName": "example_subreddit",
    "voteCount": 5,
    "commentCount": 2,
    "duration": "2 days ago",
    "upVote": false,
    "downVote": true
  }
]
```
</details>

<details>
<summary>GET `/api/posts/by-subreddit/{id}`</summary>
**Description:** Get posts by subreddit.

Parameters:

- id (path parameter) - The ID of the subreddit.

**Response**:
```json
[
  {
    "id": 12345,
    "postName": "Example Post",
    "url": "https://example.com",
    "description": "Example description",
    "userName": "example_user",
    "subredditName": "example_subreddit",
    "voteCount": 10,
    "commentCount": 5,
    "duration": "1 day ago",
    "upVote": true,
    "downVote": false
  },
  {
    "id": 54321,
    "postName": "Another Post",
    "url": "https://another.com",
    "description": "Another description",
    "userName": "another_user",
    "subredditName": "example_subreddit",
    "voteCount": 5,
    "commentCount": 2,
    "duration": "2 days ago",
    "upVote": false,
    "downVote": true
  }
]
```
</details>
<details>
<summary>GET `/api/posts/all`</summary>

**Description:** Get all posts.

**Response:**
```json
[
  {
    "id": 12345,
    "postName": "Example Post",
    "url": "https://example.com",
    "description": "Example description",
    "userName": "example_user",
    "subredditName": "example_subreddit",
    "voteCount": 10,
    "commentCount": 5,
    "duration": "1 day ago",
    "upVote": true,
    "downVote": false
  },
  {
    "id": 54321,
    "postName": "Another Post",
    "url": "https://another.com",
    "description": "Another description",
    "userName": "another_user",
    "subredditName": "another_subreddit",
    "voteCount": 5,
    "commentCount": 2,
    "duration": "2 days ago",
    "upVote": false,
    "downVote": true
  }
]
```
</details>
</details>
<details>
<summary>Comments Controller</summary>
<details>
<summary>POST `/api/comments/`</summary>

**Description:** Create a new comment.

**Request Body:**
```json
{
  "id": 12345,
  "postId": 54321,
  "createdDate": "2023-06-25T12:34:56Z",
  "text": "Example comment",
  "userName": "example_user"

}
```
</details>
<details>
  
<summary>POST `/api/comments/by-userName/{userName}`</summary>

**Description:**: Fetch all comments of a user

Parameters:

- userName (path parameter) - The username of the user .

**Response Body:**
```json
[
  {
    "id": 0,
    "postId": 0,
    "createdDate": "2023-07-18T13:19:53.462Z",
    "text": "string",
    "userName": "string"
  }
]
```
</details>
<details>
  
<summary>POST `/api/comments/by-post/{postId}` </summary>

**Description:**: Fetch all comments of a post

Parameters:

- postId (path parameter) - The Id of the post .

**Response Body:**
```json
[
  {
    "id": 0,
    "postId": 0,
    "createdDate": "2023-07-18T13:19:53.462Z",
    "text": "string",
    "userName": "string"
  }
]
```
</details>
</details>

## How to run  :wrench:
1. Clone the repository: git clone https://github.com/kpalod/ShareSpace-WEB-API
2. Set up the database connection in the application.properties file.
3. Build the project using Maven: mvn clean install
4. Run the application: mvn spring-boot:run



