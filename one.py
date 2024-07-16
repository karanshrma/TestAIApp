import gemini_api
from flask import Flask, jsonify
import google.generativeai as genai

# Initialize the Flask application
app = Flask(__name__)


genai.configure(api_key="AIzaSyDx0LELsbJQy6m5zP8_qd5ySfoMjCk9hJo")


def chatResponse(messages):

    # Create a new conversation
    response = genai.chat(messages='1 + 1')

    # Last contains the model's response:
    response.last

    print(response.last)
# chatResponse()

# Define a route for the root URL
@app.route('/api/hello<messages>', methods=['GET'])
def hello(messages):
    answer = chatResponse(messages)

    return jsonify({'message': answer})

app.run()
