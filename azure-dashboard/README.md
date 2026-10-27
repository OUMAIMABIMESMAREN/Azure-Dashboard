# Azure Management Dashboard

A full Spring Boot Maven web application simulating Microsoft Azure management tools — deployable to Azure Web App from Eclipse IDE.

---

## 📁 Project Structure

```
azure-dashboard/
├── pom.xml
└── src/main/
    ├── java/com/azure/dashboard/
    │   ├── AzureDashboardApplication.java       ← Main entry point (WAR-ready)
    │   ├── controller/
    │   │   └── DashboardController.java          ← All page routes
    │   ├── service/
    │   │   └── AzureDataService.java             ← Simulated cloud data
    │   └── model/
    │       ├── AzureResource.java
    │       ├── MonitorAlert.java
    │       ├── CostEntry.java
    │       ├── AutomationTask.java
    │       └── SecurityFinding.java
    └── resources/
        ├── application.properties
        ├── templates/
        │   ├── fragments/layout.html             ← Sidebar, topbar, head
        │   └── pages/
        │       ├── dashboard.html                ← Azure Portal home
        │       ├── monitor.html                  ← Azure Monitor
        │       ├── cost.html                     ← Cost Management
        │       ├── resources.html                ← Resource Manager
        │       ├── create-resource.html          ← Create resource form
        │       ├── automation.html               ← Azure Automation
        │       ├── create-runbook.html           ← New runbook form
        │       └── security.html                 ← Defender for Cloud
        └── static/
            ├── css/main.css
            └── js/main.js
```

---

## 🚀 Running Locally

### Prerequisites
- Java 17+
- Maven 3.8+
- Eclipse IDE (2022-12 or later recommended)

### Steps

```bash
# 1. Clone or unzip the project
cd azure-dashboard

# 2. Build
mvn clean package

# 3. Run
mvn spring-boot:run
```

Open: http://localhost:8080

### Import into Eclipse
1. **File → Import → Maven → Existing Maven Projects**
2. Browse to the `azure-dashboard` folder
3. Click **Finish**
4. Wait for Maven to download dependencies
5. Right-click project → **Run As → Spring Boot App**

---

## ☁️ Azure Deployment from Eclipse

### Step 1 — Install Azure Toolkit for Eclipse

1. Open Eclipse
2. Go to **Help → Eclipse Marketplace**
3. Search: `Azure Toolkit for Eclipse`
4. Click **Install** → accept license → restart Eclipse

Or install via update site:
- **Help → Install New Software**
- URL: `https://dl.microsoft.com/azure/plugins/eclipse`

### Step 2 — Sign In to Azure

1. In Eclipse menu: **Tools → Azure → Sign In**
   (or via Azure Explorer view: **Window → Show View → Azure Explorer**)
2. Choose **Device Login** or **Service Principal**
3. Follow browser prompts to authenticate with your Microsoft/Azure account
4. Select your **subscription** when prompted

### Step 3 — Publish as Azure Web App

1. **Right-click** the `azure-dashboard` project in Package Explorer
2. Select **Azure → Publish as Azure Web App**
3. The Azure Web App configuration dialog opens:

| Field | Value |
|---|---|
| Subscription | Your Azure subscription |
| Resource Group | Create new or select existing |
| App Service Plan | Create new (e.g., `B1` tier) |
| Web App Name | e.g., `azure-dashboard-prod` |
| Runtime | Java 17, Tomcat 10.0 |
| Region | East US (or your preferred region) |

4. Click **Deploy**
5. Eclipse uploads the WAR and configures Azure App Service automatically
6. Once deployed, your URL will be: `https://azure-dashboard-prod.azurewebsites.net`

---

## ☁️ Manual Azure CLI Deployment (Alternative)

```bash
# Login
az login

# Create resource group
az group create --name rg-azure-dashboard --location eastus

# Create App Service plan (B1 = ~$13/mo)
az appservice plan create \
  --name asp-azure-dashboard \
  --resource-group rg-azure-dashboard \
  --sku B1 \
  --is-linux

# Create Web App (Java 17 + Tomcat)
az webapp create \
  --name azure-dashboard-app \
  --resource-group rg-azure-dashboard \
  --plan asp-azure-dashboard \
  --runtime "JAVA:17-java17"

# Build WAR
mvn clean package -DskipTests

# Deploy WAR
az webapp deploy \
  --name azure-dashboard-app \
  --resource-group rg-azure-dashboard \
  --src-path target/azure-dashboard.war \
  --type war
```

---

## 🖥️ Dashboard Pages

| URL | Page | Description |
|---|---|---|
| `/` or `/dashboard` | Azure Portal Home | Summary cards, activity feed, quick nav |
| `/monitor` | Azure Monitor | Live metrics, CPU/memory charts, alert table |
| `/cost` | Cost Management | Spend trends, service breakdown, budget tracking |
| `/resources` | Resource Manager | All resources table with filter/search |
| `/resources/create` | Create Resource | Resource deployment form |
| `/automation` | Azure Automation | Runbooks table, schedule view |
| `/automation/create` | New Runbook | Runbook creation form with templates |
| `/security` | Defender for Cloud | Secure score, compliance, findings table |

---

## ⚙️ Configuration

Edit `src/main/resources/application.properties`:

```properties
server.port=8080                     # Local port
spring.thymeleaf.cache=false         # Set true for production
```

For Azure, set environment variables via App Service → Configuration:
- `JAVA_OPTS=-Xms512m -Xmx1024m`
- `SPRING_PROFILES_ACTIVE=prod`

---

## 🔧 Tech Stack

| Layer | Technology |
|---|---|
| Backend | Java 17, Spring Boot 3.2.5 |
| Templating | Thymeleaf |
| Frontend | Bootstrap 5.3, Bootstrap Icons |
| Charts | Chart.js 4.4 |
| Build | Maven (WAR packaging) |
| Deployment | Azure App Service (Tomcat) |

---

## 🔄 Redeployment

After code changes:
1. `mvn clean package`
2. Right-click project → **Azure → Publish as Azure Web App** (same config is remembered)
3. Or use Azure CLI: `az webapp deploy ...`

---

## 📝 Notes

- All data is **simulated** — no real Azure APIs are called
- The app is fully stateless — safe to scale horizontally
- WAR packaging ensures compatibility with Azure App Service (Tomcat)
- `SpringBootServletInitializer` is extended for servlet container deployment
