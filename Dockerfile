FROM openjdk:11-jre-slim  

RUN mkdir -p /opt/book3
RUN mkdir -p /opt/book3/bin  
RUN mkdir -p /opt/book3/data 
RUN mkdir -p /opt/book3/logs
RUN mkdir -p /opt/book3/usr 

# Copy the JAR and executable file to their respective locations
COPY bin/book3-1.0.jar /opt/book3/bin/
COPY book3.sh /opt/book3/

# Set the working directory to /opt/book3
WORKDIR /opt/book3

# Make the book3.sh file executable
RUN chmod +x book3.sh

# Define the entrypoint to execute the book3.sh script
ENTRYPOINT ["/opt/book3/book3.sh"]