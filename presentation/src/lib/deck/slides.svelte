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
			<h1>Automate the Migration Process</h1>
			<p>M.I.G.R.<span style="color: var(--r-link-color)">A</span>.T.E</p>
			<div class="container">
				<p class="fragment" style="color: var(--r-link-color-dark);">
					Automate both application deployment and database migration to reduce manual
					effort.
				</p>
				<ul>
					<li class="fragment">
						Use Jenkins to automate app build/deploy pipelines and database loading
						scripts.
					</li>
					<li class="fragment">
						Implement Kafka for real-time data sync between old and new systems
					</li>
					<li class="fragment">
						Automate code conversion with ANTLR visitors for app logic and MongoDB query
						generation.
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
					<li class="fragment">Monitor app performance with tools like Spring Actuator and database metrics with MongoDB Atlas.</li>
					<li class="fragment">
						Evaluate success such as app uptime, database queries latency, and easier feature updates.
					</li>
				</ul>
			</div>
		</section>

		<!-- Slide 4: Code Comparison Slide with Logo Overlay -->
		<section
			data-background-image="https://via.placeholder.com/1200x800.png?text=Comparison+Background"
		>
			<div class="slide-content">
				<h2>Legacy vs Modern Code</h2>
				<div class="two-column">
					<div>
						<h3>Legacy Code</h3>
						<pre><code class="java">
  // Legacy JBoss implementation
  public void process() &#123;
    // outdated logic here
  &#125;
              </code></pre>
					</div>
					<div>
						<h3>Modern Code</h3>
						<pre><code class="java">
  // Modern Spring Boot approach
  @RestController
  public class ProcessController &#123;
    @GetMapping("/process")
    public String process() &#123;
      return "Processed";
    &#125;
  &#125;
              </code></pre>
					</div>
				</div>
			</div>
			<div class="logo-overlay">
				<img
					src="https://via.placeholder.com/80x80.png?text=MongoDB+Logo"
					alt="MongoDB Logo"
				/>
			</div>
		</section>

		<!-- Slide 5: Bullet Points Slide -->
		<section
			data-background-image="https://via.placeholder.com/1200x800.png?text=Bullet+Points"
		>
			<div class="slide-content">
				<h2>Key Migration Tasks</h2>
				<ul>
					<li>Create a public GitHub repository</li>
					<li>Analyze the legacy kitchensink application</li>
					<li>Define the migration roadmap</li>
					<li>Implement code refactoring</li>
					<li>Integrate with MongoDB (optional)</li>
				</ul>
			</div>
			<div class="logo-overlay">
				<img
					src="https://via.placeholder.com/80x80.png?text=MongoDB+Logo"
					alt="MongoDB Logo"
				/>
			</div>
		</section>

		<!-- Slide 6: Image Slide with Background and Logo -->
		<section
			data-background-image="https://via.placeholder.com/1200x800.png?text=Architecture+Diagram"
		>
			<div class="slide-content">
				<h2 style="color: white; background: rgba(0,0,0,0.5); padding: 10px;">
					Architecture Diagram
				</h2>
			</div>
			<div class="logo-overlay">
				<img
					src="https://via.placeholder.com/80x80.png?text=MongoDB+Logo"
					alt="MongoDB Logo"
				/>
			</div>
		</section>

		<!-- Slide 7: Animated Code Walkthrough with Fragments -->
		<section
			data-background-image="https://via.placeholder.com/1200x800.png?text=Animated+Code"
		>
			<div class="slide-content">
				<h2>Animated Code Walkthrough</h2>
				<pre><code class="fragment" data-fragment-index="1">
  /* Step 1: Setup configuration */
  System.out.println("Loading configuration...");
          </code></pre>
				<pre><code class="fragment" data-fragment-index="2">
  /* Step 2: Refactor legacy code */
  refactorLegacyCode();
          </code></pre>
				<pre><code class="fragment" data-fragment-index="3">
  /* Step 3: Deploy application */
  deployApplication();
          </code></pre>
			</div>
		</section>

		<!-- Slide 8: Two-Column Slide (Code & Explanation) -->
		<section
			data-background-image="https://via.placeholder.com/1200x800.png?text=Refactoring+Details"
		>
			<div class="slide-content">
				<h2>Refactoring Details</h2>
				<div class="two-column">
					<div>
						<h3>Code</h3>
						<pre><code class="java">
  public String refactor() &#123;
    // New, optimized implementation
    return "Refactored";
  &#125;
              </code></pre>
					</div>
					<div>
						<h3>Explanation</h3>
						<p>
							The refactored method simplifies the original logic by removing
							redundant steps and enhancing performance.
						</p>
					</div>
				</div>
			</div>
			<div class="logo-overlay">
				<img
					src="https://via.placeholder.com/80x80.png?text=MongoDB+Logo"
					alt="MongoDB Logo"
				/>
			</div>
		</section>

		<!-- Slide 9: Text Overlay on Background Image with an SVG Shape -->
		<section
			data-background-image="https://via.placeholder.com/1200x800.png?text=Modern+Architecture"
		>
			<div class="slide-content">
				<h2 style="color: white; background: rgba(0,0,0,0.5); padding: 10px;">
					Modern Architecture Overview
				</h2>
				<p style="color: white; background: rgba(0,0,0,0.5); padding: 10px;">
					Leveraging modern frameworks and cloud capabilities.
				</p>
			</div>
			<div class="mongodb-shape">
				<svg viewBox="0 0 100 100">
					<polygon points="50,10 90,90 10,90" fill="rgba(0,237,100,0.3)" />
				</svg>
			</div>
		</section>

		<!-- Slide 10: Additional Bullet List Slide -->
		<section
			data-background-image="https://via.placeholder.com/1200x800.png?text=Infrastructure+%26+Tools"
		>
			<div class="slide-content">
				<h2>Infrastructure & Tools</h2>
				<ul>
					<li>Spring Boot / Quarkus on Java 21</li>
					<li>MongoDB integration</li>
					<li>Automated testing framework</li>
					<li>CI/CD pipelines</li>
				</ul>
			</div>
			<div class="logo-overlay">
				<img
					src="https://via.placeholder.com/80x80.png?text=MongoDB+Logo"
					alt="MongoDB Logo"
				/>
			</div>
		</section>

		<!-- Slide 11: Detailed Code Walkthrough with Fragments -->
		<section
			data-background-image="https://via.placeholder.com/1200x800.png?text=Detailed+Code+Walkthrough"
		>
			<div class="slide-content">
				<h2>Detailed Code Walkthrough</h2>
				<pre><code class="fragment" data-fragment-index="1">
  function setup() &#123;
    // Configure environment variables
    configureEnvironment();
  &#125;
          </code></pre>
				<pre><code class="fragment" data-fragment-index="2">
  function configureEnvironment() &#123;
    console.log("Environment configured");
  &#125;
          </code></pre>
				<pre><code class="fragment" data-fragment-index="3">
  setup();
          </code></pre>
			</div>
		</section>

		<!-- Slide 12: Migration Timeline -->
		<section
			data-background-image="https://via.placeholder.com/1200x800.png?text=Migration+Timeline"
		>
			<div class="slide-content">
				<h2>Migration Timeline</h2>
				<ul>
					<li><strong>Week 1:</strong> Project Setup & Legacy Analysis</li>
					<li><strong>Week 2:</strong> Task Breakdown & Infrastructure Setup</li>
					<li><strong>Week 3:</strong> Code Refactoring & Testing</li>
					<li><strong>Week 4:</strong> Deployment & Demo Preparation</li>
				</ul>
			</div>
		</section>

		<!-- Slide 13: Before & After Code Comparison -->
		<section
			data-background-image="https://via.placeholder.com/1200x800.png?text=Before+%26+After"
		>
			<div class="slide-content">
				<h2>Before & After</h2>
				<div class="two-column">
					<div>
						<h3>Before</h3>
						<pre><code class="java">
  // Legacy method
  public void process() &#123;
    // outdated implementation
  &#125;
              </code></pre>
					</div>
					<div>
						<h3>After</h3>
						<pre><code class="java">
  // Updated method using Spring Boot
  @RestController
  public class ProcessController &#123;
    @GetMapping("/process")
    public String process() &#123;
      return "Processed";
    &#125;
  &#125;
              </code></pre>
					</div>
				</div>
			</div>
		</section>

		<!-- Slide 14: Two-Column Slide (Text & Image) -->
		<section
			data-background-image="https://via.placeholder.com/1200x800.png?text=Project+Impact"
		>
			<div class="slide-content">
				<h2>Project Impact</h2>
				<div class="two-column" style="align-items: center;">
					<div>
						<ul>
							<li>Enhanced performance</li>
							<li>Improved maintainability</li>
							<li>Modern, scalable architecture</li>
						</ul>
					</div>
					<div>
						<img
							src="https://via.placeholder.com/400x300.png?text=Impact+Image"
							alt="Project Impact"
						/>
					</div>
				</div>
			</div>
		</section>

		<!-- Slide 15: Q&A / Discussion -->
		<section data-background-image="https://via.placeholder.com/1200x800.png?text=Q%26A">
			<div class="slide-content">
				<h2>Questions & Discussion</h2>
				<p>
					Open floor for questions and further discussion during the playback interview.
				</p>
			</div>
			<div class="logo-overlay">
				<img
					src="https://via.placeholder.com/80x80.png?text=MongoDB+Logo"
					alt="MongoDB Logo"
				/>
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
</style>
