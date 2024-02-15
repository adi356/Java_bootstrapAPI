# Java_bootstrapAPI
## To Run Project
SUGGESTED
1. Make sure you have JDK 17 binaries downloaded (can check using java -version in terminal [mac])
2. Install IntelliJ Community Edition
3. Clone git respository to IntelliJ
4. Refresh pom.xml file in IntelliJ if it is giving you an error when locating dependencies
5. You can delete the boostrap_releases.csv file before building and running the project in order to see that the application does create its own CSV file
6. Build program, then click run

### TASK (Calance OA part 1)

We often interface with applications using APIs to read and write data. Please create a small program or script that uses the GitHub API to pull information from the Bootstrap repository and write it to a file.

Repo: twbs/bootstrap using the main branch

- Use the Github API to retrieve a list of releases
- Write the list of releases to a CSV file. In the file write:
  - Created date
  - Tag Name
  - URL for the distribution zip file
