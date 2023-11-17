# :file_folder: PROJECT: API Trello - E2E scenario - Java / Rest Assured
Verification of REST API in Trello / E2E scenario using JAVA/Rest Assured

**Project scope:**
- find, read and analyse REST API documentation for Trello,
- E2E scenario:
    - create two boards,
    - create two list in board 1,
    - create two cards in list 1,
    - move card 1 from list 1 to list 2,
    - add a member to coard 1,
    - add due date to card 1,
    - archive card 1,  
    - delete the board,
- create requests and test scripts in JAVA using Rest Assured library,
- apply good practisies: clean up after testing activities,
- run tests from Maven and Jenkins,
- create Test Report in Allure.

**Tools:**
- Intellij IDEA
- Maven
- Rest Assured library
- Jenkins
- Allure Report

**Jenkins configuration:**
- Repository URL: https://github.com/AleksandraPujanek/PROJECT-API-Trello-E2E-scenario-2
- Build steps: Invoke top-level Maven targets
- Goals: clean test -Dtoken=TYPE_YOUR_TOKEN -Dkey=TYPE_YOUR_KEY -DmemberId=TYPE_YOUR_MEMBER_ID -DorganizationId=TYPE_YOUR_ORGANIZATION_ID

**Documentation:** [REST API Trello - documentation](https://developer.atlassian.com/cloud/trello/rest/api-group-actions/#api-group-actions)

**Summary:** [Trello - Test Report](https://drive.google.com/file/d/1IDqPa5oeFFC3cbYKoPzYdi5Bgwb4sywt/view?usp=drive_link)
