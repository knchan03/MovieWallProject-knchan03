#Project 1: MovieWall

##Classes:
    - project01
    - ActorWall

**project01**
    - Reads file using filepath argument given in configuration, and extracts actors, characters, and movie names to create ActorWall objects.
    - Adds all ActorWall objects into a list
    - Quicksorts the list by actor name.
    - Binary Searches for ActorWall using actor name given from scanner.
        - If actor is found, print all movies and characters.
        - If not found, suggest an actor close to input, and asks if user it is correct.
            - If yes, print all movies and roles of suggested actor.
            - If no, ask for another actor name.
    - If input is "exit", exit loop and end program.

**ActorWall**
    - Class that will store actors and their movies/characters
    - Has 2 ArrayLists that store movies and characters using same index
    - addMovie method adds movie and character into the arraylists
    - getMovies prints out all movies and roles of actor
    - getName will return name of actor, which is used to search for actor in project01 class