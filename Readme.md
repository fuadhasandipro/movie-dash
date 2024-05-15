# Movie Management System

This Movie Management System allows users to perform various operations related to managing movies, actors, directors, genres, screenings, reviews, reservations, payments, customers, and staff. It provides different functionalities tailored for Admins, Staff, and Customers.

## Admin's Functionalities:

- Add, update, remove, and view all movies.
- Add, update, remove, and view all actors.
- Add, update, remove, and view all directors.
- Add, update, remove, and view all genres.
- Add, update, remove, and view all studios.
- Add, update, remove, and view all screenings.
- Add, update, remove, and view all customers.
- Add, update, remove, and view all staff members.
- Manage movie reviews and ratings.
- Manage ticket sales and reservations.
- View financial reports and revenue statistics.
- Update self-profile.
- Update self-password.

## Staff's Functionalities:

- Manage screenings and ticket sales.
- Handle customer queries and assistance.
- View and update staff profile.
- Update self-password.

## Customer's Functionalities:

- View movie listings and showtimes.
- Reserve and purchase tickets.
- View past reservations and ticket history.
- Rate and review movies.
- Update self-information.
- View movie synopses.
- Search movies by genre, actors, directors, or keywords.
- Update self-password.

## System's Entities:

### Movie:

- Movie ID
- Title
- Description
- Genre ID
- Director ID
- Studio ID
- Release Date
- Duration (in minutes)
- Poster Image URL

### Actor:

- Actor ID
- Name
- Birthdate
- Gender
- Biography
- Image URL

### Director:

- Director ID
- Name
- Birthdate
- Gender
- Biography
- Image URL

### Genre:

- Genre ID
- Genre Name

### Screening:

- Screening ID
- Movie ID
- Location
- Start Time
- End Time
- Available Seats
- Ticket Price

### Review:

- Review ID
- Movie ID
- Customer ID
- Rating
- Comment
- Review Date

### Reservation:

- Reservation ID
- Screening ID
- Customer ID
- Number of Tickets
- Total Price
- Reservation Date

### Payment:

- Payment ID
- Reservation ID
- Amount
- Payment Date
