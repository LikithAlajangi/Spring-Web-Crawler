# Spring-Web-Crawler
This project is a multithreaded web crawler built with Java and Spring Boot, designed to fetch and parse web pages starting from a given seed URL. It supports asynchronous crawling using a thread pool, allowing multiple crawl jobs to run in parallel.

**POST "crawl/start"**

<img width="959" alt="image" src="https://github.com/user-attachments/assets/efb244ee-cde3-4125-b94c-4893eb1c2ad3" />


**GET "crawl/results"**

<img width="959" alt="image" src="https://github.com/user-attachments/assets/d2d512da-efbf-4794-91b4-b6198cb4a70b" />

## 🕸️ Web Crawler: Architecture and Flow

This project implements a multithreaded web crawler that fetches and processes URLs asynchronously. Below is a step-by-step overview of how the crawler system is designed and operates:

---

### 🔄 Crawler Workflow

#### 1. Seed URL Submission (POST Endpoint)
- A client submits a seed URL to the `/crawl` POST endpoint.
- The `CrawlController` delegates the request to the `CrawlManager` (injected using Spring’s `@Autowired`).
- A new `CrawlResult` object is created for this request, containing:
  - A unique crawl token (ID)
  - The seed URL
  - Status (`IN_PROGRESS`, `FINISHED`)
  - A list of visited URLs

#### 2. ID and CrawlResult Creation
- The `CrawlManager` generates a unique, non-duplicate crawl ID.
- A corresponding `CrawlResult` is initialized and associated with the seed URL and generated token.

#### 3. Concurrent Execution with ThreadPool
- The `CrawlManager` submits the `CrawlResult` to a thread pool via a `CrawlWorker` (which implements `Callable<CrawlResult>`).
- This setup enables concurrent execution of multiple crawl tasks in the background.
- The system supports real-time tracking of each crawl job — including progress and captured URLs.

#### 4. CrawlWorker Implementation
- The `CrawlWorker` class receives the `CrawlResult` object and implements the crawling logic inside its `call()` method.
- The crawl process follows links recursively up to the configured depth limit.

#### 5. Recursive Crawling and Result Aggregation
- During crawling, the worker continuously updates the `CrawlResult` object with discovered URLs.
- Once the crawl completes (or is interrupted), the final result is pushed to `CrawlDB`.

#### 6. CrawlDB: In-Memory Result Store
- `CrawlDB` maintains a mapping between crawl ID and its corresponding `CrawlResult` using HashMap
#### 7. Fetching Crawl Results (GET Endpoint)
- A separate endpoint `/results` is available.
- Using `@RequestParam("id")`, the client can query crawl results for a given token ID.
- This fetches the latest `CrawlResult` from `CrawlDB`.
- This allows fast, in-memory retrieval of crawl results by ID.

#### 8. JSON Response Support
- All response DTOs (e.g., `ResultsFetcherResponse`) are designed to be JSON-serializable.
- The API returns clean, structured JSON that includes:
  - Crawl status
  - List of visited URLs
  - Crawl ID
  - Source seed URL
- Tested with tools like Postman — responses are correctly formatted and human-readable.

---

### ✅ Technologies Used

- **Java**  
- **Spring Boot**  
- **Multithreading** (`ThreadPool`, `Callable`, `Future`)  
- **Jackson** (for JSON serialization)  
- **In-memory storage** (`HashMap`)





