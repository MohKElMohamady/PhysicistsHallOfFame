package com.frommstein.physicistscatalogueservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
/*import org.springframework.web.reactive.function.client.WebClient;*/

/* Spring Boot Microservices Level 1 */

	/* Part 01 The Agenda
	 *
	 * What we will be creating?
	 *
	 * We are creating a few microservices and have them communicate with each other via server discovery.
	 *
	 * We will have one microservice called PhysicistsCatalogueService that receives from the client the name of a physicist
	 * then it will call one microservice called X that will fetch all the awards given to the physicist. It will call
	 * a second microservices to retrieve all the information about these prizes.
	 * Finally, the PhysicistsCatalogueService will consolidate all of these three and send back to the front end.
	 *
	 */

	/* Part 02 Why so many technologies?
	 *
	 * One of the advantages of microservices is the ability to the make changes to small part sof the applications
	 * without having to redeploy the whole thing.
	 *
	 * Although the business logic will be different from one application to another, the problems and what programmers
	 * face between microservices, the problems that has to be solved, are common.
	 *
	 */

	/* Part 03 Microservices vs Service oriented architectures
	 * What is the difference between a pattern and a technology when it comes to microservices?
	 *
	 * In Microservices architecture a pattern is what the programmers face when it comes to coding, deploying and
	 * maintaining these microservices.
	 * Hence, programmers have established and published technologies that solve these common problems that programmers
	 * face in Microservices architecture.
	 *
	 * Just like Services in Service oriented architectures deal only with data, not UI it is the same case about
	 * microservices where other microservices act as the UI to consume the service provided by that microservices
	 */


	/* Part 05 Designing the sample microservices
	 *
	 * Initially we will be creating 3 Spring Boot projects
	 * Have the Physicists Catalogue Service call the other two services the naive way i.e calling their localhost
	 * or their website domain and finally do it the smart way which is setting up a discovery server
	 *
	 */

	/* Part 06 Creating starter microservices projects
	 * The three microservices will be created with start.spring.io
	 * We will have one microservice called PhysicistsCatalogueService that receives from the client the name of a physicist
	 * then it will call one microservice called X that will fetch all the awards given to the physicist. It will call
	 * a second microservices to retrieve all the information about these prizes.
	 * Finally, the PhysicistsCatalogueService will consolidate all of these three and send back to the front end.
	 */

	/* Part 07 Building the Physicist Catalogue Service API
	 * We will be creating a controller that retrieves the physicist id and returns the physicist name, details about him
	 * and the list of all awards and honours that he/she has won
	 *
	 */

	/* Part 08 Building the Prize Info Service
	 * I have created a controller that returns a hard coded nobel prize in physics details and the class/model that
	 * represents the prize.
	 *
	 *
	 */

	/* Part 09 Configuring server port
	 * We obviously have to change the port of the second microservice because otherwise we will run into port issues
	 * This is done via the application.properties file
	 * What is application.properties file?
	 * It allows us to specify key value pairs which can affect spring, it is a way to make spring do bunch of different
	 * things without having to change the code and compile it again.
	 * It is important to know that Tomcat server is present in every application of the microservices.
	 * For microservices, we need to have multiple instances and multiple so we have to initialize multiple tomcat servers
	 * but Spring Boot initialization via application.proprties makes it easier
	 * And this is how applications run in deployment
	 *
	 * We are actually right configuring tomcat. Normally there is a tomcat xml configuration file somewhere, but
	 * Spring Boot provides application.properties to facilitate the configuration
	 */

	/* Part 10 Coding the third service and discussing communication
	 *
	 *  TODO:
	 *   write what the third service does here
	 * We will have to call the REST APIs programmatically, which is by using a REST client library i.e RestTemplate
	 * There is also an alternate to asynchronus programming with WebClient object
	 *
	 *
	 */

	/* Part 11 Using RestTemplate to call an external microservice API
	 *
	 *  What should the PhysicistCatalogueResource do?
	 * 1) Call the Physicist Data Service to receive all the physicists
	 * 2) For each prize ID, call the Prize Info Service and get the detail
	 * 3) Put them all together
	 *
	 * To call the other microservices API, we need an instance of RestTemplate object.
	 *
	 * First we will do a little mistake of creating an instance of RestTemplate everytime the controller is called
	 * (This will be changed later)
	 * So what does RestTemplate do?
	 * It has a method called getForObject() that takes two arguments first is the url of the API that we are calling,
	 * second is the class that we will be unmarshalling the string to, this means the payload will be coming in the form
	 * of a physicist hence we can create an object that has the same properties as the coming JSON payload.
	 * What RestTemplate is create an object of that type for us,  populate the fields for us and finally provide to us
	 * the instance of that object.
	 *
	 * It is interesting to note that we are duplicating the class instances all over the microservices and this is considered
	 * bad practice in designing and developing a monolithic application but it is totally okay when developing
	 * microservices
	 *
	 * An important question to consider: What happens when these classes change? And these are changes in the API
	 * response in one microservice and the other microservices consuming it?
	 * Well, versioning in microservices is completely different animal.
	 * If we are adding a new extra field, it is fine because people who don't use it, they dont care.
	 * But if a field is removed, or its name is changed, we have to let people know or put it in a different url,
	 * so that people who using the new version do a REST call to the new url.
	 *
	 *
	 * Note: Whenever we have to create a new object in Java from a source outside Java, we need a no-arg constructor
	 */

	/* Part 12 Using a Bean to creat RestTemplate instance
	 *
	 * What we are doing wrong here? In the method of the controller, a new object of type RestTemplate is created
	 * It would make sense to have an instance of this object as a singleton.
	 *
	 * How is this possible? It is possible with creating a bean of type
	 * We will use the @Bean annotation to give us a singleton of the RestTemplate object
	 * Hence, anyone ( any class) that will autowire RestTemplate, will receive this instance of the RestTemplate
	 * Therefore we can describe @Bean as a producer and @Autowired is a consumer
	 * Note: There are two types of bean initialization: eager and lazy
	 * Lazy is when a bean is not created until a method is invoked that requires this bean
	 * Eager is when springs goes and looks for every method that is annotated with @bean and stores it and autowires
	 * them.
	 * What happens if in one application there are two beans of the same type? We get an error
	 */


	/* Part 13 Using WebClient to make API calls
	 *
	 *  RestTemplate will be deprecated soon and the alternative will be WebClient.
	 * Reactive programming deals with Flux and Mono objects
	 * In the synchronous way of programming, by the end of the execution of a method, we know that Java has provided
	 * us the object/ return type. In Reactive programming, we have API calls which set things in motion and then go
	 * on our way and do other stuff. Essentially we are telling WebClient to its job and go around and do other stuff.
	 *
	 * But how do we get our return type/ data back? When things are set in motion, we just don't tell WebClient what
	 * to do, but also give it a lambda /  callback function which executes after it is complete doing what it was tasked
	 * Hence, we are not waiting around for things to happen but we are creating a function and passing it to the
	 * asynchronous call
	 *
	 * So how do we use WebClient?
	 * The WebClient follows the method chaining pattern
	 * First method that will be called is build() to provide us an instance of WebClient
	 * Second method is the type of request that we want to do let it be get() or put() or post()
	 * Third method is the URL that we will be calling for the Http Request (equivalent to the getForObject() in RestTemplate)
	 * Fourth method is retrieve() which is asking Reactive Spring to fetch the data after giving it the sufficient information
	 * Fifth step is bodyToMono(Physicist.class) which is essentially saying whatever body your getting convert it
	 * to the class type given
	 * But what is Mono? It is type of saying that we will get an object sometime in the future but it is not yet fully
	 * completed..Kind of a promise that will get us what we want
	 * TODO: Look up that and try to understand it
	 * Final step is block(); which is telling spring to wait/ block execution until the object is completely created and hand it back
	 * to our variable that will accept it.
	 */

	/* Part 14 calling the prize microservice
	 *
	 */

	/* Part 15 Why should one avoid returning lists in APIs?
	 * Once we want to enhance the API, we cannot return a list anymore, it must convert to an object.
	 * Everytime we want to do an enhancement where the enhancement is not a part of the list, we have to change the contract
	 * that we are providing.
	 * All the clients that are expecting a list, their code will break.
	 * However, if we had an object in the first place, where one property is a list, the client's code will not break.
	 * That's why it is always better to have an object wrapper around the list.
	 */

	/* Part 16 What have we done so far
	 *
	 */

	/* Part 17 Understanding Service Discovery
	 * What are we doing wrong?
	 * Why hard coded URLs are bad? ->
	 * 1) Changes require code updates
	 * 2) Dynamic URLs in the cloud
	 * 3) Load balancing i.e let's say there is a microservice that under heavy demand and we will have mutliple instances
	 * of this microservices each having his own url, then which one will we call?
	 * 4) Multiple environments before deployment the url of the microservice changes so that we know it is in the quality
	 * and assurance phase, we do not want to check everytime for the url
	 *
	 * Hence, we have this pattern called Service Discovery
	 *
	 * We have to provide a layer of abstract i.e something in between that in charge of what these services are.
	 * We have the discovery server that is responsible for mapping the server name to the url.
	 * When the client comes it will ask the discovery server and it will ask for the url.
	 * But how does discovery server discover other microservices as well?
	 *
	 *
	 * The slight disadvantage that it requires chatting between the client and the discovery server, there is
	 * an additional step to discover it.
	 *
	 * We have an alternative which is: Server side discovery
	 * and here is how it works:
	 * The client asks to communicate with a service called x, the intermediate server will take this message and
	 * delivery it directly to service x, hence the extra hop is deleted.
	 *
	 * Spring Cloud uses client side discovery.
	 * The libraries that faciliate service discovery is on the client.
	 *
	 * Spring Cloud handles caching of discovering the server
	 */


	/* Part 18 Introducing Eureka
	 * Eureka is one of these projects that are made open source thanks to netflix.
	 * Netflix is one of the leaders in microservices libraries. Spring Boot community has created wrappers around
	 * these technologies so that we have to talk Spring and Spring will talk directly with these technologies
	 *
	 * So what will we do?
	 * First we will label the microservices that we have created as Eureka clients and the Discovery server will be
	 * labeled as Eureka Server.
	 * The API caller i.e the client will be labelled as a Eureka client as well because it wil talk with the discovery
	 * server
	 *
	 * These will be steps:
	 * 1) Start up the Eureka server
	 * 2) IMPORTANT: Have microservices register/publish using Eureka client
	 * 3) Final step: Have microservices locate(consume) using Eureka client
	 */

	/* Part 19 Starting a Eureka server
	 * The way to start a Eureka server is by starting a Spring Boot application and add the Eureka Server dependency
	 * The class that contains the main method must be annotated with @EnableEurekaServer
	 *
	 * Starting the Eureka server without any configuration will cause an error
	 * To fix these errors, the following properties need to be added in the application.properties of the Eureka server
	 * application
	 * server.port=8761
	 * eureka.client.register-with-eureka=false
	 * eureka.client.fetch-registry=false
	 * IMPORTANT
	 * These two properties are added so that Eureka server DOES NOT REGISTER ITSELF,because every Eureka Server is a
	 * Eureka client.
	 * Once a Eureka Server starts it will try to register with other Eureka servers because we will have multiple
	 * instances of them
	 */

	/* Part 20 Creating Eureka clients
	 * We have to add Spring Cloud and Eureka client dependency to each of our microservices,
	 * Our application will be registered as unknown, so we have to give it a name.
	 * This can be done inside the application.properties of each microservice and annotating each main method in
	 * every microservice with @EnableEurekaClient
	 * Thanks to the following stack overflow links that help me get this up and running because of difficulties in
	 * versioning
	 * https://stackoverflow.com/questions/51774552/spring-boot-maven-plugin-class-not-found
	 * https://stackoverflow.com/questions/69061913/beancreationexception-error-creating-bean-with-name-configurationpropertiesbea
	 */

	/* Part 21 Discovering services through Eureka
	 * How to consume microservices?
	 * Consuming it involves another annotation, once we have a rest template to do an API call to do a service
	 *
	 * RestTemplate has the ability to be gievn the APPLICATION/SERVICE NAME specified in the application.properties
	 * of each microservice and it will call the discovery server everytime.
	 * Hence we will be annotation our rest template with @LoadBalanced which tells RestTemplate to do service discovery
	 * in a load balanced way.
	 * What we are essentially saying to the RestTemplate is that do not go to the microservice discovery, rather go to
	 * the discovery server and ask it for the URL.
	 * Therefore, we will be replacing all the hardcoded localhost url with the application names!
	 *
	 */

	/* Part 22 Doing client side load balancing
	 *  What happens if the application name of one of these microservices is changed?
	 * We will be in trouble, we have to standardize the application name.
	 *
	 * One of the advantages of microservices is that there is a way to fetch all service names at runtime if we
	 * don't know ahead-of-time what the service names are
	 */

	/* Part 23 Recape and Next Steps
	 * What happens if the discovery server falls down?
	 * Thanks to caching, the clients will be knowing how to reach other microservices
	 */


	/*****************************************************LEVEL 1 ENDS HERE************************************/

/* Spring Boot Microservices Level 2 Fault tolerance and resilience */


	/* Part 01 Agenda and prerequisites
	 *	Understanding challenges with availability
	 *  Making microservices resilient and fault tolerant
	 */

	/* Part 02 Fault tolerance vs resilience
	 *	What is the difference between Fault tolerance and Resilience?
	 * These two concepts are used interchangeably
	 *
	 * Fault tolerance means: given an application, if there is a fault, how will the system be affected? We are looking
	 * at a single fault.
	 *
	 * Resilience: how many faultS can a system tolerate before it is brought down to its knees
	 */

	/* Part 03 Recap
	 *
	 */

	/* Part 04 Calling an external API
	 *
	 */

	/* Part 05 What if a microservice goes down?
	 *	What can we do to make this application resilient?
	 * First of all, we have to know even if the currently implemented system at this time is resilient or not.
	 * Bad news is, that it is not because there is no error handling
	 *
	 * How to make it resilient?
	 *
	 * First we need to understand what can go wrong with microservices?
	 *
	 * Scenario 1: a microservice instance goes down.
	 * So let's say that the prize-info-service goes down, what should we do?
	 * The physicists catalogue service will also go down
	 * The solution is to create multiple instances and have them running at different ports
	 * Thanks to service discovery, we don't have to do alot in order to get this to work
	 * When a new service is created it is registered to the Eureka server.
	 * There is a loadbalancing technology called Ribbon that
	 */

	/* Part 06 What if a microservice is slow?
	 *
	 * Scenario 2: A microservice instance is slow
	 * If a microservice in our application is slow, it is a much bigger problem.
	 * If -for example- the Prize Info service is slow, it will also affect the physicists-data-service.
	 * The call from the path physicists-catalogue-service -> physicists-data-service has nothing to do with the other
	 * path physicists-catalogue-service -> prize-info-service
	 * But still it is affecting the other path causing it to be slow. How is this happening?
	 * The answer is: THREADS!
	 *
	 */

	/* Part 07 The Problem with Threads
	 * 	How threads work in a web server?
	 *  Consider a web server, when the web server receives the request, what happens?
	 * It has to process the request and returns a response. It spawns a thread to handle it. When the response is retu-
	 * rned the thread is freed up.
	 *
	 * Now what will happen if a request comes up and it is taking time to be executed? i.e the thread is taking a while
	 * to generate the response. But in the meantime of the execution of the first request, another request comes in
	 * and spawns another thread.
	 * So where this is going? Many requests are coming up and they are not executed in time. This ends up exhausting
	 * all of the resources made available by the server.
	 * There is a configuration in TomCat server that allows us to configure the maximum number of threads in the pool.
	 * This the reason behind the slowing down of the web servers. When requests are coming up and the threads are not
	 * freed up quickly enough.
	 *
	 */

	/* Part 08 A possible solution for slow microservices
	 * Timeout!
	 * For a request that is taking  long time, we have given this request a thread and as much time as it needs,
	 * it will return an error.
	 * Increasing resources is not a solution because we need a solution even with a bigger hardware.
	 *
	 * How to set a timeout? We are using the Spring Template to do the API request.
	 * We can set up timeouts on our Spring RestTemplate, this is not however the ideal way to set up timeout.
	 *
	 */

	/* Part 09 Adding timeout to RestTemplate
	 * 	Any service that is calling an inner or an external service can have a timeout added to it.
	 * The RestTemplate can be created in such a way that we can configure it to force a timeout after X amount of seconds
	 * has passed, and it did not receive a request.
	 * Spring offers us a bean called: HttpComponentsClientHttpRequestFactory() that we can create and call a method on it
	 * to set the number of milliseconds that we want a request to maximally take.
	 * this method is called setConnectionTimeOut()
	 * After that, we will pass this factory object to the constructor of the RestTemplate.
	 */

	/* Part 10 We haven't solved it yet
	 * Does this solve the problem?
	 * When we have a timeout that is set to 3 seconds, what happens if we are receiving a request per second?
	 * We are back to the same issue. Even though the timeout will eventually solve the issue, the requests coming in
	 * are too fast. Faster than what the timer can do, to remove the resources from the previous requests.
	 * The problem can be solved as long as the requests are coming in a frequency less the frequency at which the
	 * timeout is freeing the resources.
	 * Hence this partially solves this problem.
	 */

	/* Part 11 Understanding the circuit breaker pattern
	 *	First we have to detect that prize-info-service is slow and then rather than blindly send requests,
	 * I will hold out on sending new requests, give it time recover, keep trying after a while to see if the
	 * microservice has recovered.
	 * This is a common pattern in microservices
	 * This pattern to solve the issue is called CIRCUIT BREAKER PATTERN
	 * So the steps are:
	 * 1) Detect that there is something wrong in the traffic
	 * 2) Take some temporary steps to avoid the situation getting worse, because giving more requests to the microservice
	 * is already overwhelmed with requests would make the situation worse
	 * 3) Deactivate temporarily the "problem" component so that it doesn't affect downstream components.
	 *
	 * Technically it is not possible to add a circuit breaker to every microservice.
	 * It is specially important when there is a microservice calling MULITPLE microservices, we don't want one of these
	 * microservices to be slow which would have been fast.
	 */

	/* Part 12 Circuit breaker parameters
	 * We need to have some sort of mechanism to do something when it cannot deliver it what it is asked of.
	 *
	 * When should a circuit break?
	 * There is a limit that is set, when it passes a certain limit it is activated. What are the parameters for
	 * circuit breakers in microservices?
	 *
	 * Now let's say there is a request that was successfully executed and returned a response and there is a second
	 * request coming in adding and it causes a timeout.
	 * It is illogical to cause a time out just because only one timeout has happened.
	 *
	 * What would be the trigger then?
	 * We need to know what are the parameters that we need to know to set up to enable
	 *
	 * WHEN DOES THE CIRCUIT TRIP?
	 * 1) Last n requests to consider for the decision : when something fails, it does not immediately go out.
	 * Rather it looks back in short history and see how many requests were successful and failed i.e how many of these
	 * are failing?
	 * 2) Timeout duration
	 *
	 * WHEN DOES THE CIRCUIT UN-TRIP?
	 * 1) How long after a circuit try to try again?
	 *
	 *
	 * Example:
	 * Last n requests to consider the decision: 5
	 * How many of thoses hould fail : 3
	 * Timeout duration: 2s
	 * How long to wait (sleep window): 10s
	 *
	 * If we do multiple requests to the prize-info-microservices
	 * 0.1s -> 3s -> 0.3s -> 3s -> 4s -> SLEEP WINDOW OF 10s
	 * 								^
	 * 								|
	 * 								|_ When this point is reached, it will stop sending a request
	 * So what happens after the 4s a successful request comes in that takes 0.4s? We will miss it, which is fine
	 * because we do not want to keep sending the requests to that microservice because it only make things worse.
	 * For complete reference: https://github.com/netflix/hystrix/wiki/configuration
	 *
	 * These parameters are set according to two criterias: 1)The size of the threadpool 2)The number of requests coming in
	 */

	/* Part 13 What to do when a circuit breaks?
	 *	Circuit needs to be tripped. Now what?
	 *  But there are still requests coming in to get the prizes informations. What to do?
	 *
	 * We need to have a FALLBACK mechanism
	 * What is possible?
	 * 1) Throw an error. Is it a good idea? No it is not, but it should be the last resort.
	 * 2) Return a fallback "default" response. Maybe there is a way that the caller can detect that this is a fallback
	 * response and act accordingly. That is however also not a recommended
	 * 3) Best response is to save previous responses (cache) and use that when possible.
	 * We are getting sensible information although it might not be accurate or changed but atleast we get a sensible
	 * response.
	 */

	/* Part 14 Circuit breaker benefits
	 * Why circuit breakers?
	 * 1) We need to fail fast (a good thing). For something that is about to fail, it is better to detect it early
	 * and fail it rather than waiting and making it fail later.
	 * Circuit breaker is doing so, when you realise that something is about to fail, you intervene and apply
	 * 2) Circuit breaker provide fallback functionality
	 * 3) Automatic recovery
	 *
	 * Hence the circuit breaker pattern can be summarized to these three essential steps that need to be taken
	 * When to break a circuit | What to do when a circuit breaks | When to resume requests
	 *
	 * The framework called Hystrix provides solutions for all of that.
	 *
	 * Questions collected:
	 *
	 * Q1 How do you identify the available thread pool size?
	 * A1: It is what can be configured in the servlet container i.e TomCat
	 *
	 * Q2: Is there any theory about how to determine circuit breaker parameter values?
	 * A2: There is a lot of literature that discussing these parameters, but mostly it is trial and error
	 * Such as performance test to provide expectations on how the load will be after deployment
	 *
	 * Q3:
	 *
	 * Q4: What if the cached response coming from the circuit breaker fallback is outdated?
	 * A4: There are certain cases where caching is not appropriate because the data might be sensitive for example a banking
	 * application , but in some applications like Facebook, It is obviously better to display the cached feed than
	 * return nothing.
	 *
	 *
	 */

	/* Part 15 What is Hystrix?
	 * 	Hystrix is an open source library originally created by Netflix
	 * Hystrix implements circuit breaker pattern so you don't have to
	 * It works well with Spring Boot
	 * Note: There is another pattern that is being explored called adaptive fault tolerance
	 */

	/* Part 16 Adding Hystrix to a Spring Boot app
	 *	First step is adding the Maven spring-cloud-starter-netflix-hystrix dependency
	 *  Second step: Go to the main application class in the project that requires a circuit and annotate with
	 * @EnableCircuitBreaker to the application class, Hystrix will be sitting there waiting to instruct it with the
	 * parameters for configure the circuit breaker.
	 * Third Step :We add a circuit breaker @HystrixCommand to methods that need circuit breakers
	 * Fourth Step: Configure Hystrix behavior
	 *
	 * First of, we will not configure @HystrixCommand parameters as we will be leaving the default parameters for
	 * enabling the circuit breaker.
	 * We will however give Hystrix the information for a fallback mechanism.
	 * This is done by passing a string value to the property in the annotation of fallbackMethod
	 * i.e @HystrixCommand(fallbackMethod = "getFallbackProfile")
	 * The string value is the name of the method that we will implement in the control to execute in case the microservice
	 * we are calling inside the method annotation with @HystrixCommand does not reply.
	 * Note: the method that takes over the Fallback mechanism has to be the same signature of the method annotated
	 * with @HystrixCommand
	 *
	 * So what is Hystrix doing?
	 * It detects that our method (that calls the microservice) does not get any response and executes the fallback
	 * method instead.
	 */

	/* Part 17 How does Hystrix work?
	 * Who is calling our method?
	 * How does Hystrix manage this? The answer is pretty simple.
	 * The answer is proxy.
	 * So we have our API class i.e PhysicistProfileResource that has a method annotated @HystrixCommand
	 * So what does Hystrix do? It wraps our API class in a proxy class.
	 * So when someone asks to get an instance of that our API class, it will not get our API class, rather it will get
	 * an instance of the Proxy class that contains our instance of API class.
	 * Hystrix is essentially monitoring the responses with its proxy class instance the responses that our API class
	 * receives, it examines it and returns it to our API class.
	 * If something fails however, if it sees if the result is not working, it will redirect the call to the fallback
	 * method.
	 */

	/* Part 18 Problem with Hystrix proxy
	 * Did we solve our problem? Partially.
	 * Because what we have done is we have annotated the entire method that calls BOTH methods.
	 * even if one microservice return meaningful data and does not return a logical response, it will still ignore it
	 * and execute the fallback method.
	 *
	 * So what will we do? We will extract methods out of each api calls and annotate it 
	 *
	 */

	/* Part 22 The Bulkhead pattern
	 * There is a third way in which one can deal with outages
	 * What is the Bulkhead pattern?
	 * Imagine that requests are piling up on one of the microservices to the extent that this microservice will bring
	 * another microservice down with it.
	 * The idea is - similar to the bulkhead in ships- is to create a separate compartment of thread pools
	 * for microservice x and microservice y.
	 * Separate minimum and maximum thread pool for x and separate minimum and maximum thread pool for y
	 * Water is not leaking from the x compartment to y compartment.
	 *
	 * This is also can be provided by hystrix by configuring the properties of the @HystrixCommand annotation
	 * @HystrixCommand( threadPoolKey = "prizeInfoPool",
	 * threadPoolProperties = {	@HystrixProperty(name = "coreSize", value = "20"),
	 * @HystrixProperty(name = "maxQueueSize, value = "10"),
	 * }
	 * )
	 * The first property is thread pool key.
	 * The minute we provide a thread pool key, we are creating a separate space or a separate bulkhead.
	 * We are creating a new thread pool for certain methods and then we will have multiple methods sharing the
	 * same thread pool.
	 * The next step is to configure this bulkhead:
	 * The second property is threadPoolProperties which contains @HystrixProperties that configures this bulkhead
	 * We can set the thread pool size with the property named coreSize and we can assign it a value i.e number of
	 * threads available
	 * threadPoolProperties = {	@HystrixProperty(name = "coreSize", value = "20") -> here we are setting the numbers
	 * of the threads which are 20
	 *
	 * It can also queue the request which makes it wait for a bit, just sitting and wait not consuming the resources
	 * and in the same time not return a fallback
	 * @HystrixProperty(name = "maxQuereSize", value="10") -> is setting the number of requests that has to wait
	 * until it can access and use the thread
	 *
	 */

	/* Part 23 Summary
	 * What have we done so far?
	 * 1) Understanding possible some causes for failure in microservices
	 * 2) Threads, pools and impacts of slow microservices
	 * 3) Timeouts and its limitations
	 * 4) Circuit breaker pattern
	 *
	 */
@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
public class PhysicistsCatalogueServiceApplication {

	@Bean()
	@LoadBalanced
	public RestTemplate getRestTemplate(){
		// Code from level 1
		/*return new RestTemplate();*/

		HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();

		clientHttpRequestFactory.setConnectTimeout(3000);

		return new RestTemplate(clientHttpRequestFactory);

	}

	/*@Bean
	public WebClient.Builder getWebClientBuilder(){
		return WebClient.builder();
	}*/

	public static void main(String[] args) {
		SpringApplication.run(PhysicistsCatalogueServiceApplication.class, args);
	}

}
