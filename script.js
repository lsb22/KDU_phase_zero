const getElementById = (id) => document.getElementById(id);

const minuteElement = getElementById("minutes");
const secondsElement = getElementById("seconds");
const colonElement = getElementById("colon");
const startElement = getElementById("start");
const pauseElement = getElementById("pause");
const resetElement = getElementById("reset");
const sessionElement = getElementById("sessions-completed");

let intervalId;
let pausedSecondsCount;
const originalMinute = parseInt(minuteElement.innerText);
let sessionsCount = parseInt(secondsElement.innerText);

minuteElement.innerText =
  originalMinute >= 10 ? originalMinute + "" : "0" + minuteElement.innerText;

function sessionCompleted(intervalId) {
  clearInterval(intervalId);
  alert("Hurray! Session Completed!!!!");
}

function changeStartButtonState() {
  if (startElement.innerText !== "Resume") {
    resetElement.removeAttribute("disabled");
    resetElement.style.cursor = "pointer";
  }

  startElement.setAttribute("disabled", true);
  startElement.style.cursor = "not-allowed";

  pauseElement.removeAttribute("disabled");
  pauseElement.style.cursor = "pointer";
}

function changeButtonsState() {
  startElement.removeAttribute("disabled");
  startElement.style.cursor = "pointer";

  if (startElement.innerText !== "Resume") {
    startElement.innerText = "Resume";
  }

  pauseElement.setAttribute("disabled", true);
  pauseElement.style.cursor = "not-allowed";

  pausedSecondsCount = parseInt(secondsElement.innerText);
  clearInterval(intervalId);
}

function resetClock() {
  if (startElement.getAttribute("disabled")) {
    startElement.removeAttribute("disabled");
    startElement.style.cursor = "pointer";
  }
  startElement.innerText = "Start";

  if (!pauseElement.getAttribute("disabled")) {
    pauseElement.setAttribute("disabled", true);
    pauseElement.style.cursor = "not-allowed";
  }

  resetElement.setAttribute("disabled", true);
  resetElement.style.cursor = "not-allowed";

  minuteElement.innerText =
    originalMinute >= 10 ? originalMinute + "" : "0" + originalMinute;
  secondsElement.innerText = "00";
  pausedSecondsCount = undefined;
  clearInterval(intervalId);
}

function decrementClock() {
  let minuteCount = parseInt(minuteElement.innerText);
  if (minuteCount !== 0 && startElement.innerText !== "Resume") --minuteCount;
  minuteElement.innerText =
    minuteCount >= 10 ? minuteCount.toString() : "0" + minuteCount;

  secondsElement.innerText = !pausedSecondsCount
    ? "59"
    : pausedSecondsCount >= 10
    ? pausedSecondsCount
    : "0" + pausedSecondsCount;
  let secondsCount = parseInt(secondsElement.innerText);

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
      setTimeout(() => {
        sessionCompleted(secondsInterval);
      }, 500);
      return;
    }
  }, 1000);

  intervalId = secondsInterval;
}

startElement.addEventListener("click", () => {
  decrementClock();
  changeStartButtonState();
});

pauseElement.addEventListener("click", changeButtonsState);

resetElement.addEventListener("click", resetClock);
