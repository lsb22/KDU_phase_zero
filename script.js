const getElementById = (id) => document.getElementById(id);

const minuteElement = getElementById("minutes");
const secondsElement = getElementById("seconds");
const colonElement = getElementById("colon");
const startElement = getElementById("start");
const pauseElement = getElementById("pause");
const resetElement = getElementById("reset");
const sessionElement = getElementById("sessions-completed");
const formElement = getElementById("custom-timer");

// for seconds interval
let intervalId;
// to store the inital minute value
let originalMinute = parseInt(minuteElement.innerText);
let sessionsCount = 0;

minuteElement.innerText =
  originalMinute >= 10 ? originalMinute + "" : "0" + minuteElement.innerText;

// used for setting custom time from form
function updateInitialMinutes(time) {
  minuteElement.innerText = time >= 10 ? time + "" : "0" + time;
}

// to increment sessions
function sessionCompleted(intervalId) {
  clearInterval(intervalId);
  alert("Hurray! Session Completed!!!!");
  resetClock();
}

function changeStartButtonState() {
  // enable restart button
  if (startElement.innerText !== "Resume") {
    resetElement.removeAttribute("disabled");
    resetElement.style.cursor = "pointer";
  }

  // disable start button
  startElement.setAttribute("disabled", true);
  startElement.style.cursor = "not-allowed";

  // enable pause button
  pauseElement.removeAttribute("disabled");
  pauseElement.style.cursor = "pointer";
}

function changeButtonsState() {
  startElement.removeAttribute("disabled");
  startElement.style.cursor = "pointer";

  // once you pause and resume the clock, start button
  // will areday posses Resume text
  if (startElement.innerText !== "Resume") {
    startElement.innerText = "Resume";
  }

  pauseElement.setAttribute("disabled", true);
  pauseElement.style.cursor = "not-allowed";

  clearInterval(intervalId);
}

// to reset clock to original state
function resetClock() {
  // activate start button
  if (startElement.getAttribute("disabled")) {
    startElement.removeAttribute("disabled");
    startElement.style.cursor = "pointer";
  }
  startElement.innerText = "Start";

  // disable pause button
  if (!pauseElement.getAttribute("disabled")) {
    pauseElement.setAttribute("disabled", true);
    pauseElement.style.cursor = "not-allowed";
  }

  // disable restart button
  resetElement.setAttribute("disabled", true);
  resetElement.style.cursor = "not-allowed";

  minuteElement.innerText =
    originalMinute >= 10 ? originalMinute + "" : "0" + originalMinute;
  secondsElement.innerText = "00";
  clearInterval(intervalId);
}

// begins and handles countdown logic
function decrementClock() {
  // not assigning to originalMinutes, but to minuteElement.innerText
  // , because this helps during resuming the clock
  let minuteCount = parseInt(minuteElement.innerText);
  if (minuteCount !== 0 && startElement.innerText !== "Resume") --minuteCount;
  minuteElement.innerText =
    minuteCount >= 10 ? minuteCount.toString() : "0" + minuteCount;

  let secondsCount = parseInt(secondsElement.innerText);
  secondsElement.innerText =
    secondsCount === 0
      ? "59"
      : secondsCount >= 10
      ? secondsCount
      : "0" + secondsCount;

  // update to correct seconds value
  secondsCount = parseInt(secondsElement.innerText);

  const secondsInterval = setInterval(() => {
    --secondsCount;

    if (secondsCount < 0) {
      secondsCount = 59;
      --minuteCount;
      minuteElement.innerText =
        minuteCount >= 10 ? minuteCount.toString() : "0" + minuteCount;
    }

    secondsElement.innerText =
      secondsCount >= 10 ? secondsCount.toString() : "0" + secondsCount;

    if (minuteCount == 0 && secondsCount == 0) {
      sessionsCount++;
      sessionElement.innerText =
        sessionsCount >= 10 ? sessionsCount : "0" + sessionsCount;
      // make session count update first before alert appears
      setTimeout(() => {
        sessionCompleted(secondsInterval);
      }, 500);
      return;
    }
  }, 1000);

  intervalId = secondsInterval;
}

// handle start button click
startElement.addEventListener("click", () => {
  decrementClock();
  changeStartButtonState();
});

// handle pause button click
pauseElement.addEventListener("click", changeButtonsState);

// handle reset button click
resetElement.addEventListener("click", resetClock);

// handle form sumission
formElement.addEventListener("submit", (e) => {
  resetClock();

  e.preventDefault();
  const formData = new FormData(formElement);
  originalMinute = parseInt(formData.get("custom-timer-input"));

  // reset form
  formElement.reset();
  // update custom time
  updateInitialMinutes(originalMinute);
});
