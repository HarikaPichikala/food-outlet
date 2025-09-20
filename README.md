### Problem 4 (REST API)
- **Problem 4** is *Rest API* (Question 1) from the questions document

**Files:**
- `FoodOutletController.java`
- `ApiResponse.java`
- `Outlet.java`
- `Rating.java`

**Description:**  
Fetches food outlets from a paginated API and returns the names of outlets with the highest rating in a given city.

**Features:**
* Handles pagination
* Finds max rating
* Returns all outlets with highest rating

**Input:**  
`GET /api/top-outlets?city=Houston`

**Output:**  
`["Cocoa Tree", "Paradise"]`
