# Project 1: MovieWall <br />

## Classes: <br />
    - project01
    - ActorWall

**project01** <br />
    - Reads file using filepath argument given in configuration, and extracts actors, characters, and movie names to create ActorWall objects. <br />
    - Adds all ActorWall objects into a list <br />
    - Quicksorts the list by actor name. <br />
    - Binary Searches for ActorWall using actor name given from scanner. <br />
&emsp;-If actor is found, print all movies and characters. <br />
&emsp;- If not found, suggest an actor close to input, and asks if user it is correct. <br />
&emsp;- If yes, print all movies and roles of suggested actor. <br />
&emsp;&emsp;- If no, ask for another actor name. <br />
&emsp;- If input is "exit", exit loop and end program. <br />

**ActorWall** <br />
&emsp;- Class that will store actors and their movies/characters <br />
&emsp;- Has 2 ArrayLists that store movies and characters using same index <br />
&emsp;- addMovie method adds movie and character into the arraylists <br />
&emsp;- getMovies prints out all movies and roles of actor <br />
&emsp;- getName will return name of actor, which is used to search for actor in project01 class <br />