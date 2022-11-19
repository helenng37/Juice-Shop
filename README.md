# Fruit Juice Shop

## Overview

### Main functions

This program simulates people ordering fruit juice at a kiosk in a shopping mall during a day, and then
calculate the total sales. 
This application will take the order like how many cups customers want, the type of fruit and 
the size of drink. The size options are *tall, grande and venti*.
The price list:
- A tall-size cup is 300ml: $3.50
- A grande-size cup is 500ml: $4.25
- A venti-size cup is 700ml: $5.75

There are 2 classes (**Order** and **FruitJuice**). In the **FruitJuice** class, there are 5 types of fruit 
and each can make up to 10 liters per day. So after each order, the volume of the ordered fruits will be reduced.

### Users

- The customer wants to place an order.

### Motivation

I used to be a waiter at a coffee shop. I recall that on weekends, the shop is usually very busy, so now
I want to write this application to simulate how people order and calculate how much profit the shop 
can make per day. 

## User stories

- As a customer, I want to be able to add a drink to my order.
- As a customer, I want to be able to view the list of drinks in my order.
- As a customer, I want to be able to view the total bill.
- As a customer, I want to be able to remove a juice when it is out of order.
- As a customer, I want to be able to change to another juice when the one I ordered is out of order
- As a user, I want to be able to save my order to file
- As a user, I want to be able to be able to load my order from file 

# Instructions for Grader

- You can generate the first required event related to adding a drink to an order 
  by clicking the rectangle button name "Add drink" then multiple selections of fruit type and size will appear
  Then, if you want to choose any type or size, you just need to click to the check box
- You can generate the second required event related to adding another drink to the order by following the same
  direction as adding the first drink.
- All pictures are stored in resource folder under src folder
- You can submit an order by clicking the rectangle button name "Submit order", then a message with the total bill will pop up
- You can save the state of my application by clicking the rectangle button name "Save order", and a message will pop up
- You can reload the state of my application by cling the rectangle button name "Load order".