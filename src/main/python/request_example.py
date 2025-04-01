import requests

url = "http://localhost:8084/employee"


def main():
    while 1:
        print('id:')
        choice = input('>>> ')
        if choice:
            r = requests.get(url + '/' + choice)
            if r.status_code != 200:
                if r.status_code == 404:
                    print(r.json()['message'])
                    continue
                else:
                    print('Error' + r.status_code)
            employee = r.json()
            print(employee)
        else:
            r = requests.get(url)
            employees = r.json()
            print(employees)

if __name__ == '__main__':
    main()