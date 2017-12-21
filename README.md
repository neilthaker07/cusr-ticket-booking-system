# TEAM 6 #
# CMPE 275 Term Project #

# Names #
Afreen Patel : 011811397 : afreensultana.patel@sjsu.edu 
Bhakti Shah : 011498916 : bhakti.shah@sjsu.edu 
Neil Thaker : 011538215 : neil.thaker@sjsu.edu
Purvesh Kothari : 011548615 : purvesh.kothari@sjsu.edu 

URL: http://thepk.xyz/login.html 

# Technologies & Concepts used #
Spring boot
REST APIs
Social Login Integration
RDBMS - MySQL
Transaction concept
Amazon Web Services
Frontend : HTML, Javascript, JQuery, AJAX

# Build instructions #
Step 1: Get code from bitbucket: git clone https://neilthaker07@bitbucket.org/neilthaker07/cusr-ticket-booking-system.git 
Step 2: Create MySQL Database cusr_db using this query: CREATE DATABASE cusr_db; 
Step 3: Go inside project folder cusr to start spring boot maven project on port 8080: mvn spring-boot:run 
Step 4: In Linux, go to this Apache folder : /var/www/html
Step 5: Create folder cusr. Put all web-app content into this folder for UI Apache server on port 80
Step 6: Open browser and hit this URL: http://localhost/admin.html

# Step to use CUSR App #
Step 1: To set the train capacity : http://thepk.xyz/admin.html OR  http://localhost/admin.html 
Step 2: Sign up with proper user information : http://thepk.xyz/signup.html OR  http://localhost/signup.html
Step 3: Fill up the search train form : http://thepk.xyz/search_results.html  OR  http://localhost/search_results.html
Step 4: Hit Search
Step 5: Below that 5 search results will be displayed
Step 6: Book the particular ticket 
Step 7: Fill up passenger details and confirm ticket : http://thepk.xyz/ticket-booking.html OR  http://localhost/ticket-booking.html
Step 8: Confirmation email with ticket will be sent to user
Step 9: Booking history tab in right section shows all booked tickets : http://thepk.xyz/booking-history.html OR  http://localhost/booking-history.html
Step 10: User can cancel the ticket
Step 11: User can logout. We are maintaining sessions through out the system.