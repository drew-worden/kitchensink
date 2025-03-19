<script>
	import { onMount } from "svelte"
	import Reveal from "reveal.js"
	import Markdown from "reveal.js/plugin/markdown/markdown"
	import Highlight from "reveal.js/plugin/highlight/highlight"
	import Notes from "reveal.js/plugin/notes/notes"
	import "reveal.js/dist/reveal.css"
	import "../../mongo-theme.css"

	onMount(() => {
		const deck = new Reveal()
		deck.initialize({
			plugins: [Markdown, Highlight, Notes],
			hash: true,
			slideNumber: true,
			history: true,
			center: true,
			transition: "slide",
			backgroundTransition: "slide",
			autoAnimateEasing: "ease",
			autoAnimateDuration: 1,
			controls: true,
			progress: true,
			loop: false
		})
	})
</script>

<div class="reveal">
	<div class="slides">
		<!-- Slide 1: Title Slide with Background, Logo, and Shape -->
		<section
			data-background-image="https://via.placeholder.com/1200x800.png?text=MongoDB+Background+1"
		>
			<div class="slide-content">
				<img src="mongo-logo-white.svg" alt="MongoDB Logo" class="logo" />
				<h1>Modernization Factory</h1>
				<h2 style="color: var(--r-link-color-dark)">Developer Candidate Challenge</h2>
				<p>Prepared by Drew Worden</p>
				<pre
					style="display: flex; flex-direction: column; align-items: center;">March 13th, 2025</pre>
			</div>
		</section>

		<section class="slide-content" data-auto-animate>
			<h1>Resources</h1>
			<div class="container">
				<ul>
					<li class="fragment">Code: <a target="_blank" href="https://github.com/drew-worden/kitchensink">https://github.com/drew-worden/kitchensink</a></li>
					<li class="fragment">Migrated Application: <a target="_blank" href="https://kitchensink-spring.drew.zip">https://kitchensink-spring.drew.zip/</a></li>
					<li class="fragment">Presentation: <a target="_blank" href="https://kitchensink-presentation.drew.zip">https://kitchensink-presentation.drew.zip</a></li>
				</ul>
			</div>
		</section>

		<!-- Slide 2: Introduction with Markdown Bullet Points -->
		<section class="slide-content" data-auto-animate>
			<h1>The Task</h1>
			<p>Is to turn this...</p>
			<div class="container">
				<p style="color: var(--r-link-color-dark); text-decoration: underline;">
					Kitchen Sink JBOSS Legacy Application
				</p>
				<ul>
					<li class="fragment">Built on JBOSS EAP</li>
					<li class="fragment">Java v8</li>
					<li class="fragment">Embedded H2 Database</li>
				</ul>
			</div>
		</section>

		<section class="slide-content" data-auto-animate>
			<h1>The Task</h1>
			<p>into this...</p>
			<div class="container">
				<p style="color: var(--r-link-color-dark); text-decoration: underline;">
					Kitchen Sink Spring Boot Application
				</p>
				<ul>
					<li class="fragment">Built on Spring Boot 3</li>
					<li class="fragment">Java v21</li>
					<li class="fragment">External MongoDB database</li>
				</ul>
			</div>
		</section>

		<!-- Slide 3: Code Animation Slide with a Shape -->
		<section class="slide-content" data-auto-animate>
			<h1>The Plan</h1>
			<p>M.I.G.R.A.T.E</p>
			<ul>
				<li class="fragment">
					<span style="color: var(--r-link-color)">M</span>ap the Existing System
				</li>
				<li class="fragment">
					<span style="color: var(--r-link-color)">I</span>dentify Transformation Goals
				</li>
				<li class="fragment">
					<span style="color: var(--r-link-color)">G</span>ather Tools and Resources
				</li>
				<li class="fragment">
					<span style="color: var(--r-link-color)">R</span>estructure Data and Logic
				</li>
				<li class="fragment">
					<span style="color: var(--r-link-color)">A</span>utomate the Migration Process
				</li>
				<li class="fragment">
					<span style="color: var(--r-link-color)">T</span>est Thoroughly
				</li>
				<li class="fragment">
					<span style="color: var(--r-link-color)">E</span>xecute and Evaluate
				</li>
			</ul>
		</section>

		<section class="slide-content" data-auto-animate>
			<h1>Map the Existing System</h1>
			<p><span style="color: var(--r-link-color)">M</span>.I.G.R.A.T.E</p>
			<div class="container">
				<p class="fragment" style="color: var(--r-link-color-dark);">
					Analyze both the application codebase and database structure to understand their
					interactions and dependencies.
				</p>
				<ul>
					<li class="fragment">
						Trace app logic to identify database calls and business rules.
					</li>
					<li class="fragment">
						Profile database operations to log queries and usage patterns.
					</li>
					<li class="fragment">Document app-database touchpoints and data flows.</li>
				</ul>
			</div>
		</section>

		<section class="slide-content" data-auto-animate>
			<h1>Map the Existing System</h1>
			<p><span style="color: var(--r-link-color)">M</span>.I.G.R.A.T.E</p>
			<div class="container">
				<p class="fragment" style="color: var(--r-link-color-dark);">Execution</p>
				<ul>
					<li class="fragment">
						Used tree and the IntellJ IDEA IDE's AppMap plugin to trace app logic to
						identify database calls and business rules.
					</li>
					<div style="display: grid; grid-template-columns: 1fr 1fr;">
						<img src="1.png" alt="" class="img fragment" />
						<img src="2.png" alt="" class="img fragment" />
					</div>
				</ul>
			</div>
		</section>

		<section class="slide-content" data-auto-animate>
			<h1>Identify Transformation Goals</h1>
			<p>M.<span style="color: var(--r-link-color)">I</span>.G.R.A.T.E</p>
			<div class="container">
				<p class="fragment" style="color: var(--r-link-color-dark);">
					Set goals for application/database upgrades, targeting capabilities and customer
					needs.
				</p>
				<ul>
					<li class="fragment">
						Define app goals, such as refactoring to microservices.
					</li>
					<li class="fragment">
						Define database goals, such as reducing query latency by 40%.
					</li>
					<li class="fragment">
						Collaborate with stakeholders to prioritize outcomes like easier feature
						additions.
					</li>
				</ul>
			</div>
		</section>

		<section class="slide-content" data-auto-animate>
			<h1>Identify Transformation Goals</h1>
			<p>M.<span style="color: var(--r-link-color)">I</span>.G.R.A.T.E</p>
			<div class="container">
				<p class="fragment" style="color: var(--r-link-color-dark);">Execution</p>
				<ul>
					<li class="fragment">Move to Java 21 and SpringBoot 3.x.x</li>
					<li class="fragment">Convert to controller/service/repository/dto paradigm</li>
					<li class="fragment">
						Use MongoDB as the database leveraging the Spring Data MongoDB library
					</li>
				</ul>
			</div>
		</section>

		<section class="slide-content" data-auto-animate>
			<h1>Gather Tools and Resources</h1>
			<p>M.I.<span style="color: var(--r-link-color)">G</span>.R.A.T.E</p>
			<div class="container">
				<p class="fragment" style="color: var(--r-link-color-dark);">
					Assemble tools to streamline both application refactoring and database
					migration.
				</p>
				<ul>
					<li class="fragment">
						Use ANTLR for app code conversion (e.g., COBOL to Java) and database logic
						translation.
					</li>
					<li class="fragment">
						Employ Apache NiFi for database ETL and Apache Kafka for app-database data
						streaming.
					</li>
					<li class="fragment">
						Integrate generative AI (e.g., GitHub Copilot) for drafting app and MongoDB
						code.
					</li>
				</ul>
			</div>
		</section>

		<section class="slide-content" data-auto-animate>
			<h1>Gather Tools and Resources</h1>
			<p>M.I.<span style="color: var(--r-link-color)">G</span>.R.A.T.E</p>
			<div class="container">
				<p class="fragment" style="color: var(--r-link-color-dark);">Execution</p>
				<ul>
					<li class="fragment">
						Use Spring Migrator to help do some initial conversion and then write a
						simple generative AI script to pipe certain files into.
					</li>
					<div style="display: grid; grid-template-columns: 1fr 1fr;">
						<img src="4.png" alt="" class="img fragment" />
						<img src="5.png" alt="" class="img fragment" />
					</div>
				</ul>
			</div>
		</section>

		<section class="slide-content" data-auto-animate>
			<h1>Restructure Data and Logic</h1>
			<p>M.I.G.<span style="color: var(--r-link-color)">R</span>.A.T.E</p>
			<div class="container">
				<p class="fragment" style="color: var(--r-link-color-dark);">
					Redesign the application architecture and database model to fit modern
					paradigms.
				</p>
				<ul>
					<li class="fragment">
						Refactor app logic into modular services using SOLID principles.
						abstraction.
					</li>
					<li class="fragment">
						Transform database structures into MongoDB collections.
					</li>
					<li class="fragment">
						Replace app-database calls with MongoDB driver operations.
					</li>
				</ul>
			</div>
		</section>

		<section class="slide-content" data-auto-animate>
			<h1>Restructure Data and Logic</h1>
			<p>M.I.G.<span style="color: var(--r-link-color)">R</span>.A.T.E</p>
			<div class="container">
				<p class="fragment" style="color: var(--r-link-color-dark);">Execution</p>
				<ul>
					<li class="fragment">
						Using generative AI to assist, go through and convert the appropriate
						structure.
					</li>
					<div style="display: grid; grid-template-columns: 1fr 1fr;">
						<img src="6.png" alt="" class="img fragment" />
						<img src="8.png" alt="" class="img fragment" />
					</div>
				</ul>
			</div>
		</section>

		<section class="slide-content" data-auto-animate>
			<h1>Restructure Data and Logic</h1>
			<p>M.I.G.<span style="color: var(--r-link-color)">R</span>.A.T.E</p>
			<div class="container">
				<p class="fragment" style="color: var(--r-link-color-dark);">Execution</p>
				<ul>
					<li class="fragment">
						Using generative AI to assist, go through and convert the appropriate
						structure.
					</li>
					<div style="display: grid; grid-template-columns: 1fr 1fr;">
						<img src="7.png" alt="" class="img fragment" />
						<img src="9.png" alt="" class="img fragment" />
					</div>
				</ul>
			</div>
		</section>

		<section class="slide-content" data-auto-animate>
			<h1>Automate the Migration Process</h1>
			<p>M.I.G.R.<span style="color: var(--r-link-color)">A</span>.T.E</p>
			<div class="container">
				<p class="fragment" style="color: var(--r-link-color-dark);">
					Automate both application deployment and database migration to reduce manual
					effort.
				</p>
				<ul>
					<li class="fragment">
						Given a larger application with many entities, you can identify common
						patters and write script to do the conversion automatically. For example
						converting from JBOSS services to Spring ones. This is only a good idea if
						the time investment works out.
					</li>
				</ul>
			</div>
		</section>

		<section class="slide-content" data-auto-animate>
			<h1>Test Thoroughly</h1>
			<p>M.I.G.R.A.<span style="color: var(--r-link-color)">T</span>.E</p>
			<div class="container">
				<p class="fragment" style="color: var(--r-link-color-dark);">
					Validate the migrated application and database together for functionality and
					performance.
				</p>
				<ul>
					<li class="fragment">
						Run unit tests on app services to verify business logic.
					</li>
					<li class="fragment">Test database operations to ensure data accuracy.</li>
					<li class="fragment">
						Conduct integration tests to check app-database/app-app interactions.
					</li>
				</ul>
			</div>
		</section>

		<section class="slide-content" data-auto-animate>
			<h1>Execute and Evaluate</h1>
			<p>M.I.G.R.A.T.<span style="color: var(--r-link-color)">E</span></p>
			<div class="container">
				<p class="fragment" style="color: var(--r-link-color-dark);">
					Deploy the modernized app and database, assessing outcomes against objectives.
				</p>
				<ul>
					<li class="fragment">
						Execute a phased rollout using dual-write for continuity.
					</li>
					<li class="fragment">
						Monitor app performance with tools like Spring Actuator and database metrics
						with MongoDB Atlas.
					</li>
					<li class="fragment">
						Evaluate success such as app uptime, database queries latency, and easier
						feature updates.
					</li>
				</ul>
			</div>
		</section>
		<section class="slide-content" data-auto-animate>
			<h1>Execute and Evaluate</h1>
			<p>M.I.G.R.A.T.<span style="color: var(--r-link-color)">E</span></p>
			<div class="container">
				<p class="fragment" style="color: var(--r-link-color-dark);">Execution</p>
				<ul>
					<li class="fragment">Deployment of REST API and ThymeLeaf front end</li>
					<div style="display: grid; grid-template-columns: 1fr 1fr;">
						<img src="10.png" alt="" class="img fragment" />
						<img src="11.png" alt="" class="img fragment" />
					</div>
				</ul>
			</div>
		</section>
		<section class="slide-content" data-auto-animate>
			<h1>Conclusion</h1>
			<div class="container">
				<ul>
					<li class="fragment">
						Overall, the migration was a success. The application is now running on
						Spring Boot 3 and Java 21 with a MongoDB backend. With all code being
						migrated to more modern paradigms.
					</li>
					<li class="fragment">
						In general, as the size of the codebase scales as does the complexity.
						Identifing and converting relatively isolated modules is a great way to
						attack larger projects.
					</li>
				</ul>
			</div>
		</section>
		<section class="slide-content" data-auto-animate>
			<h1>Appendix: Scalability</h1>
			<div class="container">
				<p>Strategies</p>
				<ul>
					<li class="fragment">
						Overall, the migration was a success. The application is now running on
						Spring Boot 3 and Java 21 with a MongoDB backend. With all code being
						migrated to more modern paradigms.
					</li>
					<li class="fragment">
						In general, as the size of the codebase scales as does the complexity.
						Identifing and converting relatively isolated modules is a great way to
						attack larger projects.
					</li>
				</ul>
			</div>
		</section>
	</div>
</div>

<style>
	.logo {
		width: 150px;
	}

	.container {
		box-shadow: rgb(1, 18, 26) 0px 4px 20px -4px;
		border: 1px solid rgb(61, 79, 88);
		border-radius: 24px;
		padding: 1em 1em 1.4em 1em;
	}

	.img {
		border: 1px solid rgb(61, 79, 88);
		border-radius: 24px;
	}
</style>
