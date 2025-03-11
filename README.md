
# MovieTime

**MovieTime** Android application built quick and simple as a learning project to: 

- **Learn Paging3:** Understand how to use the Paging3 library to load data in pages from both remote and local sources.
- **Explore RemoteMediator:** Discover how a RemoteMediator works as a bridge between a remote API and a local database for data pagination.
- **Create a Mapper:** a mapper to convert data from one layer like network DTOs to local database entities.

The app uses Jetpack Compose for the UI and follows Clean Architecture principles.

## Project Structure

### Data Layer

-   **Remote :**
    
    -   **MovieApi:** Defines the API endpoints using Retrofit.
    -   **MovieDto:** Data transfer object representing the movie details received from the API.
-   **Local :**
    
    -   **MovieEntity:** Room entity that represents a movie in the local database.
    -   **Converters:** Custom type converters to handle complex types like lists of genres and torrents for Room.
-   **Domain (domain):**
    
    -   **Movie:** Domain model used across the business logic and presentation layers, this abstraction keeps the core business logic independent of data source specifics.

### RemoteMediator

The `MovieRemoteMediator` plays a crucial role by bridging remote data fetching and local caching:

-   **Load Types:**
    
    -   **REFRESH:** Clears existing data and starts loading from the first page.
    -   **APPEND:** Calculates the next page based on the last loaded item.
    -   **PREPEND:** Not implemented (returns success immediately).
-   **Data Flow:**
    
    1.  **Fetch Data:** Calls `MovieApi.getMovies()` with the appropriate page and limit.
    2.  **Mapping:** Uses a mapper function (`toMovieEntity()`) to convert API DTOs to local database entities.
    3.  **Caching:** Inserts or updates the fetched data in the local Room database within a transaction.
-   **Error Handling:** Catches IO and HTTP exceptions, ensuring that any issues during data fetching are properly linked to the Paging library.
    
