package com.frommstein.physicistscatalogueservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
	 */
@SpringBootApplication
public class PhysicistsCatalogueServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PhysicistsCatalogueServiceApplication.class, args);
	}

}
