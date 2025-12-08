password = confirmation = ""

while True:
    password = input("Enter your password: ").strip()
    confirmation = input("Enter your password again: ").strip()

    # check for empty values
    if len(password) == 0:
        raise Exception("Password can't be empty")
    if len(confirmation) == 0:
        raise Exception("Confirmation value can't be empty")
    break

print("Length of the First string:", len(password))
print("Length of the Second string:", len(confirmation))

#check for length and value match 
lenMatch = "True" if len(password) == len(confirmation) else "False"
strMatch = "True" if password == confirmation else "False"

print(f"Both strings length Match: {lenMatch}")
print(f"Both strings Match: {strMatch}")
