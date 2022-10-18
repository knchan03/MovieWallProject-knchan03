# Project 1: MovieWall <br />

## Classes: <br />
    - project01 <br />
    - ActorWall <br />

**project01**
    - Reads file using filepath argument given in configuration, and extracts actors, characters, and movie names to create ActorWall objects. <br />
    - Adds all ActorWall objects into a list <br />
    - Quicksorts the list by actor name. <br />
    - Binary Searches for ActorWall using actor name given from scanner. <br />
        - If actor is found, print all movies and characters. <br />
        - If not found, suggest an actor close to input, and asks if user it is correct. <br />
            - If yes, print all movies and roles of suggested actor. <br />
            - If no, ask for another actor name. <br />
    - If input is "exit", exit loop and end program. <br />

**ActorWall** <br />
    - Class that will store actors and their movies/characters <br />
    - Has 2 ArrayLists that store movies and characters using same index <br />
    - addMovie method adds movie and character into the arraylists <br />
    - getMovies prints out all movies and roles of actor <br />
    - getName will return name of actor, which is used to search for actor in project01 class <br />