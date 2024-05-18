# Movie Management System

This Movie Management System allows users to perform various operations related to managing movies, genres, screenings, reviews, reservations, payments, customers, and staff. It provides different functionalities tailored for Admins, Staff, and Customers.

## Admin's Functionalities:

- Add, update, and view all movies.
- Add, update, and view all genres.
- Add, update, and view all screenings.
- Add, update, and view all users.
- Manage movie ratings.
- Manage ticket sales and reservations.
- Update self-profile.
- Update self-password.

## Staff's Functionalities:

- Manage screenings and ticket sales.
- View and update staff profile.
- Update self-password.

## Customer's Functionalities:

- View movie listings and showtimes.
- Reserve and purchase tickets.
- View past reservations and ticket history.
- Rate movies.
- Update self-information.
- Update self-password.

## System's Entities:

### Movie:

- Movie ID
- Title
- Description
- Genre ID
- Release Date
- Duration (in minutes)
- Poster Image URL

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
