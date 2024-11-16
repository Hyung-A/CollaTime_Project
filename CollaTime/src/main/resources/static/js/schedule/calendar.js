let currentMonth=""
document.addEventListener("DOMContentLoaded", function () {
    console.log("Details modal count on page load:", document.querySelectorAll("#detailsModal").length);

    // const makescheduleModal = createModal('schedule/makeschedule.html', 'makescheduleModal');
    //  const createsuccessModal = createModal('schedule/createsuccess.html', 'createsuccessModal');
    //const emptyModal = createModal('schedule/empty.html', 'emptyModal');
    // const listModal = createModal('schedule/list.html', 'listModal');
    //const detailsModal = createModal('schedule/details.html', 'detailsModal');

    let date = new Date();

    // 모달 동적 생성 함수
    function createModal(templatePath, id) {
        const container = document.createElement('div');
        container.id = id;
        container.style.display = 'none';
        container.style.position = 'absolute';
        container.style.zIndex = 10;

        fetch(templatePath)
            .then(res => res.text())
            .then(html => {
                container.innerHTML = html;
                document.body.appendChild(container);
            });

        return container;
    }

    // 달력 렌더링 함수
    async  function renderCalendar() {
        const viewYear = date.getFullYear();
        const viewMonth = date.getMonth();

        document.querySelector('.year-month').textContent = `${viewYear}년 ${viewMonth + 1}월`;
        currentMonth=`${viewYear}-${viewMonth + 1}`

        const prevLast = new Date(viewYear, viewMonth, 0);
        const thisLast = new Date(viewYear, viewMonth + 1, 0);

        const PLDate = prevLast.getDate();
        const PLDay = prevLast.getDay();
        const TLDate = thisLast.getDate();
        const TLDay = thisLast.getDay();

        const prevDates = [];
        const thisDates = [...Array(TLDate + 1).keys()].slice(1);
        const nextDates = [];

        if (PLDay !== 6) {
            for (let i = 0; i < PLDay + 1; i++) {
                prevDates.unshift(PLDate - i);
            }
        }

        for (let i = 1; i < 7 - TLDay; i++) {
            nextDates.push(i);
        }

        const dates = prevDates.concat(thisDates, nextDates);
        const firstDateIndex = dates.indexOf(1);
        const lastDateIndex = dates.lastIndexOf(TLDate);

        dates.forEach((date, i) => {
            let month = (viewMonth + 1).toString().padStart(2, '0'); // 기본 현재 달
            let year = viewYear.toString();                          // 기본 현재 년도

            // 지난 달 날짜 처리
            if (i < firstDateIndex) {
                month = (viewMonth === 0 ? 12 : viewMonth).toString().padStart(2, '0'); // 지난 달이 1월인 경우 12월로
                year = (viewMonth === 0 ? viewYear - 1 : viewYear).toString();          // 1월에서 지난 달은 작년으로
            }
            // 다음 달 날짜 처리
            else if (i > lastDateIndex) {
                month = (viewMonth === 11 ? 1 : viewMonth + 2).toString().padStart(2, '0'); // 다음 달이 12월인 경우 1월로
                year = (viewMonth === 11 ? viewYear + 1 : viewYear).toString();              // 12월에서 다음 달은 내년으로
            }

            const day = date.toString().padStart(2, '0'); // 날짜도 두 자리로 포맷팅
            const condition = i >= firstDateIndex && i <= lastDateIndex ? 'this' : 'other';
            dates[i] = `<div class="date" data-date="${year}-${month}-${day}" ><span class=${condition}>${date}</span></div>`;
        });



        document.querySelector('.dates').innerHTML = dates.join('');

        const today = new Date();
        if (viewMonth === today.getMonth() && viewYear === today.getFullYear()) {
            for (let date of document.querySelectorAll('.this')) {
                if (+date.innerText === today.getDate()) {
                    date.classList.add('today');
                    break;
                }
            }
        }



        await getSchedules();
    }

    renderCalendar();

    const prevMonth = () => {
        date.setMonth(date.getMonth() - 1);
        renderCalendar();
    };

    const nextMonth = () => {
        date.setMonth(date.getMonth() + 1);
        renderCalendar();
    };

    const goToday = () => {
        date = new Date();
        renderCalendar();
    };

    document.querySelector(".go-prev").addEventListener("click", prevMonth);
    document.querySelector(".go-next").addEventListener("click", nextMonth);
    document.querySelector(".go-today").addEventListener("click", goToday);


});

async  function getSchedules() { // 항상 promise 반환


    const urlParam=new URLSearchParams(window.location.search);
    const projectNo=urlParam.get('projectNo');

    await  fetch(`/api/schedules/user/${projectNo}`, {cache: "no-store"}) // await 는 promise 처리될 때 까지 기다림
        .then(response => response.json())
        .then(schedules => {
            if (schedules && schedules.length >= 0) {
                calendarDisplaySchedules(schedules);
            }});

    document.querySelectorAll('.date').forEach(dateItem => {
        const scheduleItem = dateItem.querySelectorAll('.schedule-item');
        if (scheduleItem.length === 0) {
            // handleDateClick 이벤트 추가
            const dataDate = $(dateItem).data("date");

            // 이벤트 핸들러를 클릭 시점에 호출되도록 수정
            dateItem.addEventListener('click', () => emptyDateClick(dataDate));
        }
    });

}


function emptyDateClick(dataDate) {
    // $("#emptyModal").show();
    // $("#middleModal").show();
    const html =`<div id="centeredContent">
            <img src="/css/schedule/image/emptycalendar.png" id="centerImage">
                <p id="centerText">일정을 등록하세요.</p>
        </div>`;


    $("#scheduleList").html(html);




    $("#scheduleList").addClass("emptyModal");

    $("#list-plusList").on("click", function(){
        $("#scheduleStartDate").val(dataDate);
        $("#scheduleEndDate").val(dataDate);
        $("#makeScheduleModal").find(".readonly").removeClass("readonly");
        $("#makeScheduleModal").show();
    });

    $("#list-listXButton").on("click", function(){
        $("#listModal").hide();
    });

    $("#listModal").show();

}



function displaySchedules(schedules) {
    console.log(" displaySchedules  가져오기 :");

    const scheduleList = document.getElementById("scheduleList");
    scheduleList.innerHTML = "";

    schedules.forEach(schedule => {
        const item = document.createElement("div");
        item.classList.add("schedule-item");
        item.textContent = schedule.scheduleTitle;
        item.addEventListener("click", () => openDetailsModal(schedule.scheduleNo));
        scheduleList.appendChild(item);
    });
}



function calendarDisplaySchedules(schedules) {
//console.log("displaySchedules 가져오기:");
    const dates = $(".date");

    schedules.forEach(schedule=> {
        const item = document.createElement("div");
        item.classList.add("schedule-item");
        //item.addEventListener("click", listModalOpen(schedules));
        item.setAttribute("data-schedules-no", schedule.scheduleNo);
        item.setAttribute("data-scheduleStartDate", schedule.scheduleStartDate);
        item.setAttribute("data-scheduleEndDate", schedule.scheduleEndDate);


        dates.each(function(dateIndex, data) {
            const dateValue = $(this).data("date");

            if (dateValue >= schedule.scheduleStartDate && dateValue <= schedule.scheduleEndDate) {
                item.textContent ="";
                item.style.backgroundColor = schedule.colorCode;
                if(dateValue==schedule.scheduleStartDate){
                    item.textContent = schedule.scheduleTitle;
                    item.style.color= schedule.textColorCode;
                }

                $(this).append(item.cloneNode(true));
            }

        });
    });

    $(".schedule-item").on("click", function(e) {
        const parentNode= $(this).parent();
        const dataDate = parentNode.data("date");
        const dataSscheduleItemAll = parentNode.find(".schedule-item");

        let scheduleNoList = [];
        dataSscheduleItemAll.each(function(){
            const scheduleNo = $(this).data("schedules-no");
            scheduleNoList.push(scheduleNo);
        });
        listModalOpen(scheduleNoList, dataDate);
    });
}

async function listModalOpen(scheduleNoList, dataDate) {
//console.log("listModalOpen:", scheduleNoList);
    $("#scheduleList").removeClass("emptyModal");

    const urlParam=new URLSearchParams(window.location.search);
    const projectNo=urlParam.get('projectNo');

    console.log("scheduleNoList ===== :",scheduleNoList);

    try {
        // POST 요청으로 변경
        const response = await fetch(`/api/schedules/scheduleNoList`, {
            method: "POST", // GET -> POST로 변경
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ scheduleNoList, projectNo })
        });

        if (!response.ok) {
            throw new Error("서버 요청 실패");
        }

        const schedules = await response.json();

        // #scheduleList에 스케줄 목록 추가
        displayScheduleList(schedules,dataDate);

        // 모달 표시
        $("#listModal").show();
    } catch (error) {
        console.error("에러 발생:", error);
    }
}


// 스케줄 목록 표시 함수
function displayScheduleList(schedules,dataDate) {
    const scheduleListContainer = document.getElementById("scheduleList");
    scheduleListContainer.innerHTML = ""; // 기존 내용을 초기화

    schedules.forEach(schedule => {
        // 스케줄 항목 생성
        const scheduleItem = document.createElement("div");
        scheduleItem.classList.add("schedule-card");
        scheduleItem.style.backgroundColor = schedule.colorCode;

        console.log(" 스케줄 파티원 : schedule :",schedule.participantList);
        let participantNameList = "";
        if (schedule.participantList.length > 0) {
            schedule.participantList.forEach(participant => {
                participantNameList += `<p style="color: ${schedule.textColorCode}" ><strong>팀원:</strong> ${participant.nickName}</p>`;


            });
        }

        // HTML 내용 구성
        scheduleItem.innerHTML = `
        <div class="schedule-header">
            <h3 style="color: ${schedule.textColorCode}">${schedule.scheduleTitle}</h3>
        </div>
        <div class="schedule-body">
            <p style="color: ${schedule.textColorCode}" ><strong>기간:</strong> ${schedule.scheduleStartDate} ~ ${schedule.scheduleEndDate}</p>
            <p style="color: ${schedule.textColorCode}" >${schedule.scheduleContent}</p>
            <p class="schedule-creator" style="color: ${schedule.textColorCode}" >작성자: ${schedule.scheduleCreator}</p>

            ${participantNameList}
        
          <div class="schedule-footer">
            <button class="view-btn" data-id="${schedule.scheduleNo}">상세보기</button>
          </div>
        </div>
    `;

        // #scheduleList에 추가
        scheduleListContainer.appendChild(scheduleItem);

        // 상세보기 버튼 클릭 시
        const viewButton = scheduleItem.querySelector(".view-btn");
        viewButton.addEventListener("click", function () {
            displayScheduleDetail(schedule); // 상세보기 함수 호출
        });


        $("#list-listXButton").on("click", function () {
            $("#listModal").hide();
        });

        $("#list-plusList").on("click", function () {
            $("#scheduleStartDate").val(dataDate);
            $("#scheduleEndDate").val(dataDate);
            $("#makeScheduleModal").find(".readonly").removeClass("readonly");
            $("#makeScheduleModal").show();
        });


    });

}

// 상세보기 기능
function displayScheduleDetail(schedule) {
// 상세보기 페이지에 정보 채우기
    $('#detailsModal input[name="scheduleNo"]').val(schedule.scheduleNo);
    $('#detailsModal .scheduleTitle input').val(schedule.scheduleTitle);
    $('#detailsModal .scheduleStartDate').val(schedule.scheduleStartDate);
    $('#detailsModal .scheduleEndDate').val(schedule.scheduleEndDate);
    $('#detailsModal textarea[name="scheduleContent"]').val(schedule.scheduleContent);
    $('#detailsModal .creator-info').html(`생성자 | ${schedule.scheduleCreator}`);

    console.log("================displayScheduleDetail상세보기 :",schedule.participantList);


    let detailsParticipantNameList="";
    if (schedule.participantList.length > 0) {
        schedule.participantList.forEach(participant => {
            detailsParticipantNameList +=`
        <span class="participant-item" data-memberno="${participant.participantNo}"> ${participant.nickName}
            <button type="button" class="remove-btn update-remove-btn" data-memberno="${participant.participantNo}"  
            onclick="deleteParticipant(this)"               
            >x</button>         
           <span class="update-participant-item-span"> ,</span>    
        </span>
    `;
        });
    }
    $("#detailsModal #update-participant-list").html(detailsParticipantNameList);




    // 색상 선택 옵션
    $('#detailsModal .color-button').css('background-color', schedule.colorCode);
    $('#detailsModal input[name="colorCode"]').val(schedule.colorCode);

    // 글자색 선택 옵션
    $('#detailsModal .textColorPicker').val(schedule.textColorCode);
    $('#detailsModal .selectedTextColor').val(schedule.textColorCode);

    // 참여자 정보 채우기
    const participantList = $('#detailsModal .participant-list');
    if (schedule.participants) {
        participantList.html(schedule.participants.map(participant => `<span>${participant}</span>`).join(", "));
    }

    // 모달 띄우기
    $('#detailsModal').show();
}

function  deleteParticipant(event) {
    $(event).parent().remove();
}




