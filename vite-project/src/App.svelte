<script>
  let currentLogin;
  let newName;
  let newEmail;
  let newQuestion;
  let options = [""];
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
    update();
  }

  function addOption () {
    options[options.length] = ""
  }

  function placeVote(question, answer) {
    fetch("http://localhost:8080/vote", {
      method: "PUT",
      body: JSON.stringify({
        voter : {
          name : newName,
          email : newEmail
        },
        question: question,
        answer: answer
      }),
      headers: {
        "Content-Type": "application/json"
      }
    }).then((response) => {
      if (response.status === 201) {
        alert(`User ${newName} successfully created a poll!`);
        voteOnPolls()
      }
    }).catch((error) => {
      alert(error.message);
    })
    update()
  }

  function createNewPoll() {
    fetch("http://localhost:8080/createPoll", {
      method: "POST",
      body: JSON.stringify({
        creator: {
          name: newName,
          email: newEmail
        },
        question: newQuestion,
        options: options
      }),
      headers: {
        "Content-Type": "application/json"
      }
    }).then((response) => {
      if (response.status === 201) {
        alert(`User ${newName} successfully created a poll!`);
        voteOnPolls()
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
        alert(`User ${newName} successfully created!`);
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
    result = fetch("http://localhost:8080/polls").then((response) => response.json())
  }

</script>

<div>
  <h1>Poll App</h1>
    {#if currentLogin != null}
      <div>
        <button on:click={logOut}>Log Out</button>
        {#if voting === true}
        <button on:click={createPoll}>Create Poll</button>
          {:else if creatingPoll === true}
        <button on:click={voteOnPolls}>Vote On Polls</button>
          {/if}
          </div>
      {#if creatingPoll === true}
      <div class="create-poll">
        <h2>Create new Poll</h2>
        <label for="create-name">Question:</label>
        <input  id="create-name" type="text" bind:value={newQuestion}>

          {#each options as option, i}
            <div>
            <label for="create-option">  Option {i + 1}:</label>
            <input  id="create-option{i}" type="text" bind:value={options[i]}>
            </div>
          {/each}
        <button on:click={addOption}> Add Option</button>
        <button on:click={createNewPoll} >Create</button>
      </div>
      {/if}
      {#if voting === true}
        {#await result}
          it's loading..
          {:then polls}
          {#each polls as poll}
            <div>
              <p>Creator: {poll.creator.name}</p>
              <h2>{poll.question}</h2>
              {#each poll.options as option}
              <p>
                {option.caption}
                {#if option.votes.length >= 1}
                  {#each option.votes as vote}
                    {#if vote.voter.name !== currentLogin}
                      <button on:click={placeVote}>Vote</button>
                    {/if}
                  {/each}
                  {:else}
                  <button on:click={() => placeVote(poll.question, option.caption)}>Vote</button>
                {/if}
              </p>
              {/each}
            </div>

          {/each}
          {/await}
        {/if}
      {:else}
      <div class="create-user">
        <h2>Create new user or log in</h2>
        <label for="create-name">Name:</label>
        <input  id="create-name" type="text" bind:value={newName}>
        <label for="create-email">Email:</label>
        <input  id="create-email" type="text" bind:value={newEmail}>
        <button on:click={createNewUser} >Create</button>
      </div>
    {/if}
</div>