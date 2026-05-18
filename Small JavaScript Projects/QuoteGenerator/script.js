// Variables

let btn = document.querySelector(".quote-btn");
let quote = document.querySelector(".quote");
let author = document.querySelector(".author");

const quotes = [
    {
      quote: "Any application that can be written in JavaScript, will eventually be written in JavaScript.",
      author: "Jeff Atwood"
    },
    {
      quote: "JavaScript’s popularity is almost entirely independent of its qualities as a programming language.",
      author: "Douglas Crockford"
    },
    {
      quote: "Java is to JavaScript what car is to carpet.",
      author: "Chris Heilmann"
    },
    {
      quote: "The strength of JavaScript is that you can do anything. The weakness is that you will.",
      author: "Reg Braithwaite"
    },
    {
      quote: "If you deep-dive into JavaScript, you’ll find a beautiful language hidden inside a messy one.",
      author: "Douglas Crockford"
    },
    {
      quote: "First, solve the problem. Then, write the code.",
      author: "John Johnson"
    },
    {
      quote: "Experience is the name everyone gives to their mistakes.",
      author: "Oscar Wilde"
    },
    {
      quote: "In order to be irreplaceable one must always be different.",
      author: "Coco Chanel"
    },
    {
      quote: "Knowledge is power.",
      author: "Francis Bacon"
    },
    {
      quote: "Code is like humor. When you have to explain it, it’s bad.",
      author: "Cory House"
    },
    {
      quote: "Fix the cause, not the symptom.",
      author: "Steve Maguire"
    },
    {
      quote: "Optimism is a occupational hazard of programming: feedback is the treatment.",
      author: "Kent Beck"
    },
    {
      quote: "Simplicity is the soul of efficiency.",
      author: "Austin Freeman"
    },
    {
      quote: "Before software can be reusable it first has to be usable.",
      author: "Ralph Johnson"
    },
    {
      quote: "Make it work, make it right, make it fast.",
      author: "Kent Beck"
    },
    {
        quote: "Talk is cheap. Show me the code.",
        author: "Linus Torvalds"
      },
      {
        quote: "Programs must be written for people to read, and only incidentally for machines to execute.",
        author: "Harold Abelson"
      },
      {
        quote: "The best error message is the one that never shows up.",
        author: "Thomas Fuchs"
      },
      {
        quote: "The most damaging phrase in the language is: It’s always been done this way.",
        author: "Grace Hopper"
      },
      {
        quote: "Computers are good at following instructions, but not at reading your mind.",
        author: "Donald Knuth"
      },
      {
        quote: "Simplicity is prerequisite for reliability.",
        author: "Edsger W. Dijkstra"
      },
      {
        quote: "The only way to go fast, is to go well.",
        author: "Robert C. Martin"
      },
      {
        quote: "Truth can only be found in one place: the code.",
        author: "Robert C. Martin"
      }
  ];

btn.addEventListener("click", newQuote);

function newQuote() {
    let index = Math.floor((Math.random() * quotes.length));
    let newQuote = quotes[index].quote;
    let newAuthor = quotes[index].author;
    
    quote.innerText = newQuote;
    author.innerText = newAuthor;
}