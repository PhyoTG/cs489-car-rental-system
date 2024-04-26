# cs489-car-rental-system
CS489 Project


# Problem Statement
Fairfield Fleet Car Rental(PF) is a company that are in the business of managing a
growing network of rental cars across cities in the Mid West regions.
Assume you have been hired by the company, as a Lead Software Engineer and
tasked to lead the effort in designing and developing a web-based software solution (i.e. a
website) which the company will be using to manage their business.

The system will be used by Regional Managers to register Car Owners and Renters who want to join the
network of rental cars. Each Owner and Renter(ie. customer) is given a unique ID number and their First Name,
Last Name, Contact Phone Number, Email are recorded into the system. For the validation for the customer before making booking,
the customers need to show the valid identification from the Iowa such as Driver License issued by Iowa.
The Office Manager also uses the system to enroll car along with the VIN number, model, made, mileage,year, rental rate.
A Customer can login the system to see for available cars based on location, date, and category and to request Booking.
Upon receiving a request for a Booking, the Regional Manager can then book the
car and the system will send a confirmation email notifying the Renters as well as to car owners and the
appointment gets recorded accordingly.

Car Owners should be able to sign-in to the system and view a listing of all the Bookings of their cars,
including details of the Customers who they have been requested.
The system should provide information about each car, including its owner name, location address,telephone number and where to pick up the car.

Customers should be able to sign-in to the system and view their bookings, including the information of
the cars which they have been booked to rent. Customers should also be able to request to cancel or change their booking date.
The regional manager should be able to check the available cars if the appointment have been changed or cancelled.
There will be panelty fee if the change request is within 36 hours before Booking Time.

There will be return date time and will make payment offline with the Regional Manager Office.Online Payment will be implemented in the next Sprint.
Regional Manager will insert amount of fees, including late fee, damage.

# Requirements
[Requirements.txt](ProjectRelated%2FRequirements.txt)

# Class Diagram
![Class Diagram.png](ProjectRelated%2FDiagram%2FClass%20Diagram.png)

# High Level Architecture
![Architexture.png](ProjectRelated%2FDiagram%2FArchitexture.png)

# ER Diagram
![ERDiagram.png](ProjectRelated%2FDiagram%2FERDiagram.png)

# PostMan
You can see PostMan Collection Here https://api.postman.com/collections/4350390-10ef63e0-6eb7-4fac-959b-a4b38c701632?access_key=PMAT-01HWC455PJNMK3086WZM7377SG

# Screenshots on Azure

Login API call on Azure
![Azure Login.png](ProjectRelated%2FScreenshots%2FAzure%20Login.png)

Create Customer
![Azure Create Customer.png](ProjectRelated%2FScreenshots%2FAzure%20Create%20Customer.png)

Get All Customers
![Azure Get All Customers.png](ProjectRelated%2FScreenshots%2FAzure%20Get%20All%20Customers.png)

Screenshot of DB
![Azure DB Tables.png](ProjectRelated%2FScreenshots%2FAzure%20DB%20Tables.png)

GitHub Action Deployment on Main Branch
![Github Action CICD.png](ProjectRelated%2FScreenshots%2FGithub%20Action%20CICD.png)

# Testing
![Test Pass.png](ProjectRelated%2FScreenshots%2FTest%20Pass.png)
![Unit Test Pass.png](ProjectRelated%2FScreenshots%2FUnit%20Test%20Pass.png)
