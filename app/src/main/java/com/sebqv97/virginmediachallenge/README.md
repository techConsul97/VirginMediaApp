#VIRGIN MEDIA CHALLENGE

##Description

*Virgin Money* would like a directory app to allow staff to:
- See all of their colleagues contact details
- See which rooms in the office are currently occupied.

##Requirements

- using Kotlin and a min SDK 19
- list/detail pages for the people and a list page for the rooms
- structured in such a way that the app is easily **testable**
- ability to quickly pull up and use the contact details of any of their colleagues. *All details* of the contact should be displayed in the app
- must work across phones and tablets
- app **should** be accessible

##Additional information:

###Data source
 
- The API that provides the necessary data is located at `https://
61e947967bc0550017bc61bf.mockapi.io/api/v1/ ` and is RESTful with 2
resources:
^^^^
  - people
  - rooms
^^^^
Both support `GET` requests to list the data and also to directly access
individual records (the API is read only)
  
    
###Brand color

-Virgin Money requests us to use this color: '#C40202'


##Done when:

*ALL* Requirements are met.




#================================================================================================#
#==========================================STEPS=================================================#

##Business logic:
 1. Things concerning data: API, Repository Layer, ViewModels: 2 viewModels, 1 for Rooms, 1 for People, Models generated from JSON:2 of them
 2. Injection of dependencies: Hilt: set @ApplicationPoint in Application(), @EntryPoints for each fragment, @ViewModel for the viewModel


##UI:

1 Main Activity that needs to be declared Entry Point
3 fragments: 2 for Material Tabs, 1 for UserDetails, meaning we need to create a navGraph
SwipeRefresh option 

