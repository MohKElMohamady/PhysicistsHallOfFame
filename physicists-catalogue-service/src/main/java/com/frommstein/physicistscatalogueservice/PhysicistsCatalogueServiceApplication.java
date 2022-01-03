package com.frommstein.physicistscatalogueservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
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
@SpringBootApplication
public class PhysicistsCatalogueServiceApplication {

	@Bean()
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}

	/*@Bean
	public WebClient.Builder getWebClientBuilder(){
		return WebClient.builder();
	}*/

	public static void main(String[] args) {
		SpringApplication.run(PhysicistsCatalogueServiceApplication.class, args);
	}

}
