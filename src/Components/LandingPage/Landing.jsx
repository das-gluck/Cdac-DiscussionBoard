import React from "react";
import "./LandingPage.css"; // Import the CSS file

const LandingPage = () => {
  return (
    <div className="bg-gray-100">
      {/* Navbar */}
      <nav className="bg-white shadow-md p-4 flex justify-between items-center fixed w-full top-0 z-10">
        <div className="flex items-center space-x-4">
          <span className="text-xl font-bold text-blue-600 ml-10">ChatHive</span>
        </div>
        <ul className="hidden md:flex space-x-20 text-gray-700 font-medium">
          <li><a href="login" className="hover:text-blue-600">Dashboard</a></li>
          <li><a href="#why" className="hover:text-blue-600">WhyChatHive</a></li>
          <li><a href="#hangout" className="hover:text-blue-600">Hangout</a></li>
          <li><a href="#footer" className="hover:text-blue-600">Support</a></li>
        </ul>
        <button className="bg-blue-600 text-white px-4 py-2 rounded-lg mr-10" onClick={() => window.location.href = '/login'}>Login</button>
      </nav>

      {/* Hero Section */}
      <section className="hero-section text-white text-center py-20 flex items-center">
        <div className="w-1/2 hero-image h-96 mt-10"></div>
        <div className="w-1/2 text-left p-10">
          <h1 className="text-5xl font-bold">Your Place to Talk</h1>
          <p className="mt-4 text-lg">
            ChatHive brings communities together for easy communication and collaboration.
          </p>
          <div className="mt-6">
            <button className="bg-white text-blue-600 px-6 py-2 rounded-lg mr-4" onClick={() => window.location.href = '/login'}>Login</button>
            <button className="bg-black text-white px-6 py-2 rounded-lg" onClick={() => window.location.href = '/signup'}>Register</button>
          </div>
        </div>
      </section>

      {/* Invite Section */}
      <section id="why" className="p-10 bg-white flex items-center">
        <div className="w-1/2 invite-image"></div>
        <div className="w-1/2 text-left p-10">
          <h2 className="text-3xl font-bold">An invite-only place with plenty of room to talk</h2>
          <p className="mt-4">
            Discussion-Board servers are organized into topic-based channels where you can collaborate, share, and just talk about your day without clogging up a group chat.
          </p>
        </div>
      </section>

      {/* Where Hanging Out is Easy */}
      <section id="hangout" className="p-10 bg-gray-200 flex items-center">
        <div className="w-1/2 text-left p-10">
          <h2 className="text-3xl font-bold">Where hanging out is easy</h2>
          <p className="mt-4">
            Grab a seat in a voice channel when you're free. Friends in your server can see you're around and instantly pop in to talk without having to call.
          </p>
        </div>
        <div className="w-1/2 hangout-image"></div>
      </section>

      {/* Reliable Tech Section */}
      <section className="p-10 bg-white text-center">
        <h2 className="text-3xl font-bold">Reliable tech for staying close</h2>
        <p className="mt-4">
          Low-latency voice and video feels like you're in the same room. Wave hello over video, watch friends stream their games, or gather up and have a drawing session with screen share.
        </p>
        <div className="mt-6 flex justify-center reliable-tech-image"></div>
      </section>

      {/* Footer */}
      <footer id="footer" className="bg-gray-800 text-white text-center p-6">
        <p>Your Place to Talk</p>
        <p>© 2025 ChatHive. All rights reserved.</p>
      </footer>

    </div>
  );
};

export default LandingPage;
