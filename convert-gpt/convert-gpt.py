#!/usr/bin/env python3
import argparse
from dotenv import load_dotenv
import os
import sys
from openai import OpenAI

# Load environment variables from .env file
load_dotenv()

def main():
    # Parse command line arguments
    parser = argparse.ArgumentParser(
        description="Convert a JBOSS application file to a Spring Boot application file using GPT."
    )
    parser.add_argument("filepath", help="Path to the JBOSS application file")
    args = parser.parse_args()

    # Verify the file exists
    if not os.path.exists(args.filepath):
        print(f"Error: The file '{args.filepath}' does not exist.")
        sys.exit(1)

    # Read file content
    try:
        with open(args.filepath, "r", encoding="utf-8") as file:
            file_content = file.read()
    except Exception as e:
        print(f"Error reading file: {e}")
        sys.exit(1)

    # Create the prompt for conversion
    prompt = (
        "You are a conversion assistant. Please convert the following JBOSS application file "
        "to a Spring Boot application file. Ensure that the resulting file uses proper Spring Boot conventions:\n\n"
        f"{file_content}\n\nConverted Spring Boot file:"
    )

    client = OpenAI()

    # Call the OpenAI GPT API for conversion
    try:
        response = client.chat.completions.create(
            model="gpt-4o",
            messages=[
                {
                    "role": "system",
                    "content": (
                        "You are a helpful assistant that converts JBOSS application files to Spring Boot application files."
                    )
                },
                {"role": "user", "content": prompt}
            ],
            max_tokens=1500,
            temperature=0.7
        )
    except Exception as e:
        print(f"Error calling the OpenAI API: {e}")
        sys.exit(1)

    # Extract and display the converted file content
    converted_file = response.choices[0].message.content
    print("Converted Spring Boot File:\n")
    print(converted_file)

if __name__ == '__main__':
    main()
