<script>
  let currentLogin;
  let newName;
  let newEmail;
  let newQuestion;
  let newOption1;
  let newOption2;
  let creatingPoll = false;
  let voting = true;

  function logOut() {
    currentLogin = null;
  }

  function createPoll() {
    creatingPoll = true;
    voting = false;
  }

  function voteOnPolls () {
    voting = true;
    creatingPoll = false;
  }

  function createNewPoll() {
    fetch("http://localhost:8080/createUser", {
      method: "POST",
      body: JSON.stringify({
        creator: {
          name: newName,
          email: newEmail
        },
        question: newQuestion,
        options: [
          {
            "caption": "sol",
            "presentationOrder": 0,
            "votes": []
          },
          {
            "caption": "måne",
            "presentationOrder": 1,
            "votes": []
          }
        ]
      }),
      headers: {
        "Content-Type": "application/json"
      }
    }).then((response) => {
      if (response.status === 201) {
        alert(`User ${newName} successfully created!`);
        newName = "";
        newEmail = "";
      }
    }).catch((error) => {
      alert(error.message);
    })
  }

  function createNewUser() {
    currentLogin = newName;
    fetch("http://localhost:8080/createUser", {
      method: "POST",
      body: JSON.stringify({
        name: newName,
        email: newEmail
      }),
      headers: {
        "Content-Type": "application/json"
      }
    }).then((response) => {
      if (response.status === 201) {
        newName = "";
        newEmail = "";
      }
      if (response.status === 400) {
        newName = null;
        newEmail = null;
        currentLogin = null;
        alert("Username and Email does not match")
      }
    }).catch((error) => {
      alert(error.message);
    })
    update();
  }

let result;

  function update() {
    result = fetch("http://localhost:8080/users").then((response) => response.json())
  }

</script>

<div>
  <h1>Poll App</h1>
    {#if currentLogin != null}
      <div>
        <button on:click={logOut}>Log Out</button>
        <button on:click={createPoll}>Create Poll</button>
        <button on:click={voteOnPolls}>Vote On Polls</button>
      </div>
      {#if creatingPoll === true}
      <div class="create-poll">
        <h2>Create new Poll</h2>
        <label for="create-name">Question:</label>
        <!-- The bind: syntax is used to establish two-way databinding with a variable -->
        <input  id="create-name" type="text" bind:value={newQuestion}>
        <div>
          <label for="create-email">  Option 1:</label>
          <input  id="create-email" type="text" bind:value={newOption1}>
        </div>
        <div>
          <label for="create-email">  Option 2:</label>
          <input  id="create-email" type="text" bind:value={newOption2}>
        </div>

        <!-- The on:click registers an event handler, i.e. a function to be called (Command pattern) -->
        <button on:click={createNewPoll} >Create</button>
      </div>
      {/if}
      {#if voting === true}
        <p>Nothing yet</p>
        {/if}
      {:else}
      <div class="create-user">
        <h2>Create new user or log in</h2>
        <label for="create-name">Name:</label>
        <!-- The bind: syntax is used to establish two-way databinding with a variable -->
        <input  id="create-name" type="text" bind:value={newName}>
        <label for="create-email">Email:</label>
        <input  id="create-email" type="text" bind:value={newEmail}>
        <!-- The on:click registers an event handler, i.e. a function to be called (Command pattern) -->
        <button on:click={createNewUser} >Create</button>
      </div>
    {/if}

      </div>