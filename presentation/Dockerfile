# Stage 1: Build the SvelteKit app
FROM node:18-alpine AS builder
WORKDIR /app

# Copy package files and install dependencies
COPY package*.json ./
RUN npm install

# Copy the rest of the application code and build the app
COPY . .
RUN npm run build

# Stage 2: Deploy the built application
FROM node:18-alpine
WORKDIR /app

# Copy package files from the builder stage and install only production dependencies
COPY --from=builder /app/package*.json ./
RUN npm install --production

# Copy the built output from the builder stage
COPY --from=builder /app/build ./build

# Expose the port (adjust if your app uses a different port)
EXPOSE 3000

# Start the Node server using the built entry point
CMD ["node", "build/index.js"]
