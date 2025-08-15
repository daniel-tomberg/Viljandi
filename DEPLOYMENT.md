# Deployment Guide

## Frontend (Vercel) - ✅ Already Deployed

Your frontend is already deployed at: **https://frontend-ed8t102do-danieltombergwork-gmailcoms-projects.vercel.app**

### Environment Variables in Vercel

1. Go to your Vercel dashboard: https://vercel.com/danieltombergwork-gmailcoms-projects/frontend/settings
2. Navigate to "Environment Variables"
3. Add the following variable:
   - **Name**: `NEXT_PUBLIC_API_URL`
   - **Value**: `https://your-backend-url.com/api` (update this after deploying your backend)
   - **Environment**: Production, Preview, Development

## Backend Deployment Options

### Option 1: Railway (Recommended)

1. **Sign up**: Go to [Railway](https://railway.app/) and sign up with GitHub
2. **Create project**: Click "New Project" → "Deploy from GitHub repo"
3. **Select repository**: Choose your GitHub repository
4. **Configure deployment**:
   - **Root Directory**: Set to `/backend` ⭐ (This is crucial!)
   - **Build Command**: Leave empty (handled by .nixpacks)
   - **Start Command**: `java -jar target/customer-order-portal-0.0.1-SNAPSHOT.jar`
5. **Environment Variables**: Add any necessary environment variables
6. **Deploy**: Railway will automatically build and deploy your application using the `railway.toml` configuration

### Option 2: Render

1. **Sign up**: Go to [Render](https://render.com/) and sign up
2. **Create service**: Click "New" → "Web Service"
3. **Connect repository**: Connect your GitHub repository
4. **Configure service**:
   - **Name**: `customer-order-portal-backend`
   - **Root Directory**: `backend`
   - **Build Command**: `./mvnw clean package -DskipTests`
   - **Start Command**: `java -jar target/customer-order-portal-0.0.1-SNAPSHOT.jar`
   - **Environment**: Java
5. **Deploy**: Click "Create Web Service"

### Option 3: Heroku

1. **Sign up**: Go to [Heroku](https://heroku.com/) and sign up
2. **Install CLI**: `npm install -g heroku`
3. **Login**: `heroku login`
4. **Create app**: `heroku create your-app-name`
5. **Set buildpack**: `heroku buildpacks:set heroku/java`
6. **Deploy**: `git push heroku main`

## Database Setup

Your application currently uses H2 in-memory database. For production, consider:

1. **PostgreSQL on Railway/Render**: Add PostgreSQL service to your deployment
2. **Update application.yml**: Configure database connection
3. **Environment Variables**: Set database URL, username, password

## CORS Configuration

Make sure your backend allows requests from your Vercel frontend domain. Update your Spring Security configuration if needed.

## Final Steps

1. **Deploy backend** using one of the options above
2. **Update frontend environment variable** in Vercel with your backend URL
3. **Test the application** by visiting your Vercel URL
4. **Set up automatic deployments** by connecting your GitHub repository to Vercel

## Troubleshooting

### Common Issues

1. **CORS errors**: Ensure your backend allows requests from your Vercel domain
2. **Environment variables**: Make sure `NEXT_PUBLIC_API_URL` is set correctly
3. **Database connection**: Verify database credentials and connection string
4. **Build failures**: Check that all dependencies are properly configured

### Useful Commands

```bash
# Test backend locally
cd backend
./mvnw spring-boot:run

# Test frontend locally
cd frontend
npm run dev

# Build backend
cd backend
./mvnw clean package

# Deploy to Vercel
cd frontend
vercel --prod
```
